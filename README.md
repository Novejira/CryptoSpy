
# CryptoSpy


Pastikan Anda selalu siap menghadapi volatilitas pasar kripto dengan CryptoSpy, aplikasi Android yang akan menjadi sahabat setia Anda dalam melacak dan mengelola investasi kripto. Dengan fitur-fitur unggul seperti pelacakan portofolio, notifikasi harga, agregator berita, grafik live, konverter mata uang, serta keamanan dan privasi yang terjamin, CryptoSpy memudahkan Anda untuk tetap terinformasi dan mengambil keputusan investasi yang cerdas. Dengan antarmuka yang ramah pengguna dan cakupan fitur yang komprehensif, CryptoSpy adalah pilihan terbaik bagi para pedagang kripto, baik yang sudah berpengalaman maupun yang baru memulai. Unduh sekarang dan rasakan kemudahan mengelola investasi kripto Anda di mana pun dan kapan pun!


## API Reference

#### Get all coins

```http
curl --request GET \
     --url 'https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&per_page=20&sparkline=false&precision=2' \
     --header 'accept: application/json' \
     --header 'x-cg-demo-api-key: ${api_key}'
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Get item

```http
curl --request GET \
     --url 'https://api.coingecko.com/api/v3/coins/${id}?localization=false&tickers=false&market_data=true&community_data=false&developer_data=false&sparkline=false' \
     --header 'accept: application/json' \
     --header 'x-cg-demo-api-key: ${api_key}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of coin to fetch |
| `api_key`      | `string` | **Required**. Your API key|


## Team Member

- [Muhammad Asnafi Alkaromi](https://github.com/asnafialkaromi) (Ketua)
- [Ulin Nuha](https://github.com/UlinNuha89) (Wakil Ketua)
- [Raihan Rafi Rizqullah](https://github.com/RaihanRafi44) (Anggota)
- [Rafi Ammar Dinata](https://github.com/rafiammr) (Anggota)
- [Novejira Angela Pello](https://github.com/Novejira) (Anggota)
## Preview App

![App Screenshot](https://raw.githubusercontent.com/asnafialkaromi/ImagaeSource/main/Preview%20Crypto%20Spy/Preview1.png)

![App Screenshot](https://raw.githubusercontent.com/asnafialkaromi/ImagaeSource/main/Preview%20Crypto%20Spy/Preview2.png)

