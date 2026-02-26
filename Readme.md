## API Endpoints

| Method | URL | Description | Screenshot |
|--------|-----|-------------|------------|
| GET | `http://localhost:8080/api/books` | Get all books |<img width="551" height="362" alt="Screenshot 2026-02-25 192126" src="https://github.com/user-attachments/assets/9d703d6f-e65c-4fe2-a4b6-24aae21222d9" />
 |
| GET | `http://localhost:8080/api/books/1` | Get book by ID | <img width="800" height="502" alt="image" src="https://github.com/user-attachments/assets/dab80080-9910-43b8-83ce-54cf7c251546" />
|
| POST | `http://localhost:8080/api/books` | Create a new book |<img width="1024" height="743" alt="image" src="https://github.com/user-attachments/assets/b7035994-a34e-4a24-84ed-0f9d78f78761" />|
| GET | `http://localhost:8080/api/books/search?title=java` | Search by title | <img width="1149" height="739" alt="image" src="https://github.com/user-attachments/assets/6fbeede7-007e-421e-ab14-3254f8aeaec7" />
|
| GET | `http://localhost:8080/api/books/price-range?minPrice=30&maxPrice=50` | Filter by price range | <img width="1335" height="675" alt="image" src="https://github.com/user-attachments/assets/5b997444-da0b-40d2-a1a4-1219adad5b48" />
|
| GET | `http://localhost:8080/api/books/sorted?sortBy=price&order=asc` | Get sorted books |<img width="1349" height="766" alt="image" src="https://github.com/user-attachments/assets/51526b24-2bba-45fb-a1f9-8f6cf107a287" />
 |
| PUT | `http://localhost:8080/api/books/1` | Full update a book | <img width="836" height="598" alt="image" src="https://github.com/user-attachments/assets/7e78ed10-bfc6-45a6-b5ff-2578d55c8ab5" />
|
| PATCH | `http://localhost:8080/api/books/1` | Partial update a book | <img width="766" height="602" alt="image" src="https://github.com/user-attachments/assets/c13f39fb-bb1d-4cd9-b4d2-0c60084e0c2f" />
|
| DELETE | `http://localhost:8080/api/books/1` | Delete a book |<img width="1231" height="766" alt="image" src="https://github.com/user-attachments/assets/867c4487-5335-4e54-b69d-59076ccb4c1b" />
 |
| GET | `http://localhost:8080/api/books/paginated?page=0&size=5` | Get paginated books |<img width="1337" height="766" alt="image" src="https://github.com/user-attachments/assets/7465f9a5-e60a-4d0b-a0f4-bbf0bea3cef4" />
 |
| GET | `http://localhost:8080/api/books/filter?title=java&sortBy=price&order=asc&page=0&size=3` | Filter + sort + paginate |<img width="1203" height="758" alt="image" src="https://github.com/user-attachments/assets/ce811ad6-09a3-4c02-8fa4-212d84d1d9c8" />
 |
