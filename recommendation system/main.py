from flask import Flask, request, jsonify
import pandas as pd
import numpy as np
from sklearn.preprocessing import OneHotEncoder, MinMaxScaler
from sklearn.metrics.pairwise import cosine_similarity

pd.set_option('display.max_columns', None)
pd.set_option('display.max_rows', None)
pd.set_option('display.width', 200)

app = Flask(__name__)
data = pd.read_csv("C:/Users/Tuf/OneDrive/Desktop/licenta/sport/src/main/resources/static/data/megaGymDataset.csv", usecols=['Title', 'BodyPart', 'Equipment', 'Rating']).dropna(subset=['Rating'])


data.reset_index(drop=True, inplace=True)
data.insert(0, 'ID', data.index)

# codific bodypart si equipment in vectori binari (ex: 'Abdominals' o sa fie [1 0 0 ... 0], urmatorul o sa fie [0 1 0 ... 0] etc..)
one_hot_encoder = OneHotEncoder()
encoded_features = one_hot_encoder.fit_transform(data[['BodyPart', 'Equipment']]).toarray()

# normalizez si rating-ul intre 0 si 1
scaler = MinMaxScaler()
data['rating_normalized'] = scaler.fit_transform(data[['Rating']])

# fiecare exercitiu va avea astea 3 caracteristici concatenate intr-un singur array
features = np.hstack([encoded_features, data[['rating_normalized']].values])

# features = np.hstack([encoded_features])
# constuiresc matricea de similaritate pentru toate exercitiile
similarity_matrix = cosine_similarity(features)


def get_similar_exercises(exercise_id, top_n):
    # iau datele exercitiului meu
    base_exercise = data.iloc[exercise_id]
    base_body_part = base_exercise['BodyPart']
    base_equipment = base_exercise['Equipment']

    # sortez desc. in functie de similaritatea cu exercitiul meu
    similar_indices = np.argsort(-similarity_matrix[exercise_id])

    # iau exercitii care au acelasi boodypart, echipament diferit, excluzand exercitiul meu
    similar_exercises = data.iloc[similar_indices]
    filtered_exercises = similar_exercises[
        (similar_exercises['BodyPart'] == base_body_part) &
        (similar_exercises['Equipment'] != base_equipment) &
        (similar_exercises['ID'] != base_exercise['ID'])
        ]

    # iau primele 3 care sa aiba si intre ele echipamente diferite
    unique_equipment_exercises = filtered_exercises.drop_duplicates(subset=['Equipment']).head(top_n)

    return unique_equipment_exercises[['ID', 'Title', 'BodyPart', 'Equipment', 'Rating']]

print(data)

#pt cele care au la bodypart NaN adica null le pun "N/A"
data.fillna("N/A", inplace=True)

@app.route('/recommend', methods=['GET'])
def recommend():
    exercise_id = int(request.args.get('exercise_id'))
    top_n = int(request.args.get('top_n', 3))
    recommendations = get_similar_exercises(exercise_id, top_n)
    result = recommendations.to_dict(orient='records')
    return jsonify(result)

@app.route('/all_exercises', methods=['GET'])
def all_exercises():
    result = data.to_dict(orient='records')
    return jsonify(result)

if __name__ == '__main__':
    app.run(port=5000)







# print(data)
# print()
# print()
# print(data.iloc[[1]])
# print()
# print()
# print(get_similar_exercises(1, 3))

