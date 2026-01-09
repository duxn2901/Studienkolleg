N, M = map(int, input().split())

genres = {}

for i in range (1, N+1):
    genre_name = input().strip()
    genre_code = f"TL{i:03d}"
    genres[genre_code] = genre_name

class Series:
    def __init__(self, serie_code, genre_name, released_date, serie_name, episodes):
        self.serie_code = serie_code
        self.genre_name = genre_name
        self.released_date = released_date
        self.serie_name = serie_name
        self.episodes = episodes

        d, m, y = map(int, released_date.split('/'))
        self.released_date_formatted = (y, m, d)

series = []

for i in range(1, M+1):
    genre_code = input().strip()
    released_date = input().strip()
    serie_name = input().strip()
    episodes = int(input().strip())
    serie_code = f"P{i:03d}"
    genre_name = genres[genre_code]

    series.append(Series(serie_code, genre_name, released_date, serie_name, episodes))

series.sort(key = lambda x: (x.released_date_formatted, x.serie_name, -x.episodes))

for serie in series:
    print(serie.serie_code, serie.genre_name, serie.released_date, serie.serie_name, serie.episodes)