// CINEMA CONTENT MANAGEMENT SYSTEM
// MongoDB (BSON Document Format)
// Database: cinema_db

use("cinema_db")

// COLLECTION: movies

{
  "id": 1,
  "title": "Avatar",
  "release_year": 2009,
  "duration": 162,
  "rating": "PG-13",
  "actors": [
    {
      "actorId": 1,
      "full_name": "Sam Worthington",
      "nationality": "Australian"
    },
    {
      "actorId": 2,
      "full_name": "Zoe Saldana",
      "nationality": "American"
    }
  ],
  "genres": [
    "Sci-Fi",
    "Adventure"
  ]
},
{
  "id": 2,
  "title": "Biznes po kazahsky",
  "release_year": 2010,
  "duration": 148,
  "rating": "PG-13",
  "actors": [
    {
      "actorId": 3,
      "full_name": "Nurlan Koyanbayev",
      "nationality": "Kazakh"
    },
    {
      "actorId": 4,
      "full_name": "Tom Hardy",
      "nationality": "British"
    }
  ],
  "genres": [
    "Sci-Fi",
    "Thriller"
  ]
}


// COLLECTION: halls

{
  "id": 1,
  "hall_name": "Hall A",
  "capacity": 120
},
{
  "id": 2,
  "hall_name": "Hall B",
  "capacity": 80
}

// ============================================
// COLLECTION: schedules
// ============================================

{
  "id": 1,
  "movie_id": 1,
  "hall_id": 1,
  "show_date": "2026-04-20",
  "show_time": "18:00"
},
{
  "id": 2,
  "movie_id": 2,
  "hall_id": 2,
  "show_date": "2026-04-20",
  "show_time": "20:00"
}

// ============================================
// COLLECTION: customers
// ============================================

{
  "id": 101,
  "full_name": "Nariman Suleimenov",
  "email": "nariman@mail.com",
  "tickets": [
    {
      "ticketId": 1,
      "movie": "Avatar",
      "seat": "A1",
      "price": 3000
    }
  ]
},
{
  "id": 102,
  "full_name": "Zhansaya Tolebai",
  "email": "tolebai@mail.com",
  "tickets": [
    {
      "ticketId": 2,
      "movie": "Inception",
      "seat": "B5",
      "price": 3500
    }
  ]
}

// ============================================
// COLLECTION: tickets
// ============================================

{
  "id": 1,
  "customer_id": 101,
  "schedule_id": 1,
  "seat_number": "A1",
  "price": 3000
},
{
  "id": 2,
  "customer_id": 102,
  "schedule_id": 2,
  "seat_number": "B5",
  "price": 3500
}

// ============================================
// COLLECTION: staff
// ============================================

{
  "id": 1,
  "full_name": "John Manager",
  "position": "Manager",
  "supervisor_id": null
},
{
  "id": 2,
  "full_name": "Sara Worker",
  "position": "Cashier",
  "supervisor_id": 1
},
{
  "id": 3,
  "full_name": "Mike Worker",
  "position": "Cleaner",
  "supervisor_id": 1
}
