////package  org.example;
////import com.mongodb.client.*;
////import org.bson.Document;
////
////import static com.mongodb.client.model.Filters.eq;
////
////public class Main {
////    public static void main(String[] args) {
////        try (MongoClient client = MongoClients.create("mongodb+srv://narik:Nariman17@cluster0.nhpbper.mongodb.net/?retryWrites=true&w=majority")) {
////            MongoDatabase db = client.getDatabase("library_db");
////            MongoCollection<Document> collection = db.getCollection("members");
////
////            System.out.println("Connected to MongoDB!");
////
////            insert(collection);
////            read(collection);
////            update(collection);
////            delete(collection);
////
////        } catch (Exception e) {
////            System.err.println("Ошибка подключения или работы:");
////            e.printStackTrace(); // Это покажет точную причину в консоли
////        }
////        }
////
////        // CREATE
////        static void insert(MongoCollection<Document> collection) {
////            Document doc = new Document("name", "Mongo User")
////                    .append("email", "mongo@mail.com")
////                    .append("status", "Active");
////
////            collection.insertOne(doc);
////            System.out.println("Inserted!");
////        }
////
////        // READ
////        static void read(MongoCollection<Document> collection) {
////            System.out.println("Documents:");
////
////            FindIterable<Document> docs = collection.find();
////
////            for (Document doc : docs) {
////                System.out.println(doc.toJson());
////            }
////        }
////
////        // UPDATE
////        static void update(MongoCollection<Document> collection) {
////            collection.updateOne(
////                    eq("email", "mongo@mail.com"),
////                    new Document("$set", new Document("status", "Suspended"))
////            );
////
////            System.out.println("Updated!");
////        }
////
////        // DELETE
////        static void delete(MongoCollection<Document> collection) {
////            collection.deleteOne(eq("email", "mongo@mail.com"));
////            System.out.println("Deleted!");
////        }
////    }
//
//
////package org.example;
////
////import com.mongodb.client.*;
////import com.mongodb.client.model.Filters;
////import com.mongodb.client.model.Sorts;
////import com.mongodb.client.model.Updates;
////import com.mongodb.client.result.DeleteResult;
////import com.mongodb.client.result.UpdateResult;
////import org.bson.Document;
////
////import java.util.Arrays;
////import java.util.Date;
////
////public class Main {
////
////    static final String CONNECTION_STRING =
////            "mongodb+srv://narik:Nariman17@cluster0.nhpbper.mongodb.net/" +
////                    "?retryWrites=true&w=majority";
////
////    public static void main(String[] args) {
////        try (MongoClient client = MongoClients.create(CONNECTION_STRING)) {
////
////            MongoDatabase db = client.getDatabase("library_db");
////
////            MongoCollection<Document> books   = db.getCollection("books");
////            MongoCollection<Document> members = db.getCollection("members");
////
////            System.out.println("Connected to MongoDB!\n");
////
////            // ── BOOKS CRUD ─────────────────────────────────────────
////            System.out.println("===== BOOKS =====");
////            insertBook(books);
////            readBooks(books);
////            updateBook(books);
////            deleteBook(books);
////            System.out.println("Books after delete:");
////            readBooks(books);
////
////            // ── MEMBERS CRUD ───────────────────────────────────────
////            System.out.println("\n===== MEMBERS =====");
////            insertMember(members);
////            readMembers(members);
////            updateMember(members);
////            deleteMember(members);
////            System.out.println("Members after delete:");
////            readMembers(members);
////
////        } catch (Exception e) {
////            System.err.println("Error: " + e.getMessage());
////            e.printStackTrace();
////        }
////    }
////
////    // ══════════════════════════════════════════════════════════
////    //  BOOKS CRUD
////    // ══════════════════════════════════════════════════════════
////
////    // CREATE – insert a new book with embedded authors & categories
////    static void insertBook(MongoCollection<Document> books) {
////        // Remove if already exists so re-runs stay clean
////        books.deleteMany(Filters.eq("isbn", "ISBN003"));
////
////        Document book = new Document()
////                .append("title",        "Introduction to Algorithms")
////                .append("isbn",         "ISBN003")
////                .append("publication_year", 2009)
////                .append("available",    true)
////                .append("authors", Arrays.asList(              // EMBEDDED authors
////                        new Document("name", "Thomas H. Cormen")
////                                .append("nationality", "American"),
////                        new Document("name", "Charles E. Leiserson")
////                                .append("nationality", "American")
////                ))
////                .append("categories", Arrays.asList("Algorithms", "Computer Science")); // EMBEDDED
////
////        books.insertOne(book);
////        System.out.println("[INSERT] Book added: " + book.getString("title"));
////    }
////
////    // READ – print all books sorted by title
////    static void readBooks(MongoCollection<Document> books) {
////        System.out.println("[SELECT] All books:");
////        for (Document doc : books.find().sort(Sorts.ascending("title"))) {
////            System.out.println("  Title    : " + doc.getString("title"));
////            System.out.println("  ISBN     : " + doc.getString("isbn"));
////            System.out.println("  Year     : " + doc.getInteger("publication_year"));
////            System.out.println("  Available: " + doc.getBoolean("available"));
////            System.out.println("  ---");
////        }
////    }
////
////    // UPDATE – mark ISBN003 as unavailable
////    static void updateBook(MongoCollection<Document> books) {
////        UpdateResult result = books.updateOne(
////                Filters.eq("isbn", "ISBN003"),
////                Updates.set("available", false)
////        );
////        System.out.println("[UPDATE] Books modified: " + result.getModifiedCount());
////    }
////
////    // DELETE – remove ISBN003
////    static void deleteBook(MongoCollection<Document> books) {
////        DeleteResult result = books.deleteOne(Filters.eq("isbn", "ISBN003"));
////        System.out.println("[DELETE] Books removed: " + result.getDeletedCount());
////    }
////
////    // ══════════════════════════════════════════════════════════
////    //  MEMBERS CRUD
////    // ══════════════════════════════════════════════════════════
////
////    // CREATE – insert a new member
////    static void insertMember(MongoCollection<Document> members) {
////        members.deleteMany(Filters.eq("email", "mongo@mail.com"));
////
////        Document doc = new Document()
////                .append("full_name",   "Mongo User")
////                .append("email",       "mongo@mail.com")
////                .append("phone",       "+7-700-000-0001")
////                .append("joined_date", new Date())
////                .append("status",      "Active");
////
////        members.insertOne(doc);
////        System.out.println("[INSERT] Member added: " + doc.getString("full_name"));
////    }
////
////    // READ – print all members
////    static void readMembers(MongoCollection<Document> members) {
////        System.out.println("[SELECT] All members:");
////        for (Document doc : members.find().sort(Sorts.ascending("full_name"))) {
////            System.out.printf("  %-20s | %-25s | %s%n",
////                    doc.getString("full_name"),
////                    doc.getString("email"),
////                    doc.getString("status"));
////        }
////    }
////
////    // UPDATE – suspend the member
////    static void updateMember(MongoCollection<Document> members) {
////        UpdateResult result = members.updateOne(
////                Filters.eq("email", "mongo@mail.com"),
////                Updates.set("status", "Suspended")
////        );
////        System.out.println("[UPDATE] Members modified: " + result.getModifiedCount());
////    }
////
////    // DELETE – remove the member
////    static void deleteMember(MongoCollection<Document> members) {
////        DeleteResult result = members.deleteOne(Filters.eq("email", "mongo@mail.com"));
////        System.out.println("[DELETE] Members removed: " + result.getDeletedCount());
////    }
////}
//
//
//package org.example;
//
//import com.mongodb.client.FindIterable;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//
//import com.mongodb.client.model.Filters;
//import com.mongodb.client.model.Sorts;
//import com.mongodb.client.model.Updates;
//
//import com.mongodb.client.result.DeleteResult;
//import com.mongodb.client.result.UpdateResult;
//
//import org.bson.Document;
//
//import java.util.Arrays;
//import java.util.Date;
//
//public class Main {
//
//    // =====================================================
//    // MongoDB Atlas Connection
//    // =====================================================
//    static final String CONNECTION =
//            "mongodb+srv://narik:Nariman17@cluster0.nhpbper.mongodb.net/" +
//                    "?retryWrites=true&w=majority";
//
//    public static void main(String[] args) {
//
//        try (MongoClient client = MongoClients.create(CONNECTION)) {
//
//            MongoDatabase db = client.getDatabase("library_db");
//
//            // Collections
//            MongoCollection<Document> books   = db.getCollection("books");
//            MongoCollection<Document> members = db.getCollection("members");
//            MongoCollection<Document> loans   = db.getCollection("loans");
//
//            System.out.println("✅ Connected to MongoDB Atlas");
//            System.out.println("Database: library_db\n");
//
//            // =============================================
//            // Insert sample data (2+ docs each collection)
//            // =============================================
//            seedBooks(books);
//            seedMembers(members);
//            seedLoans(loans);
//
//            // =============================================
//            // CRUD DEMO (members collection)
//            // =============================================
//            System.out.println("========== CRUD ON MEMBERS ==========\n");
//
//            insertMember(members);
//            readMembers(members);
//
//            updateMember(members);
//            readMembers(members);
//
//            deleteMember(members);
//            readMembers(members);
//
//            System.out.println("✅ Program finished successfully.");
//
//        } catch (Exception e) {
//            System.out.println("❌ Error:");
//            e.printStackTrace();
//        }
//    }
//
//    // =====================================================
//    // TASK 2 - SAMPLE DATA
//    // =====================================================
//
//    // BOOKS = EMBEDDING authors + categories
//    static void seedBooks(MongoCollection<Document> books) {
//
//        if (books.countDocuments() > 0) return;
//
//        Document b1 = new Document()
//                .append("title", "Database Systems")
//                .append("isbn", "ISBN001")
//                .append("publication_year", 2022)
//                .append("authors", Arrays.asList(
//                        new Document("authorId", 1).append("name", "John Doe"),
//                        new Document("authorId", 2).append("name", "Anna Smith")
//                ))
//                .append("categories",
//                        Arrays.asList("Computer Science", "Databases"));
//
//        Document b2 = new Document()
//                .append("title", "Advanced SQL")
//                .append("isbn", "ISBN002")
//                .append("publication_year", 2023)
//                .append("authors", Arrays.asList(
//                        new Document("authorId", 3).append("name", "Mark Brown")
//                ))
//                .append("categories",
//                        Arrays.asList("Programming", "SQL"));
//
//        books.insertMany(Arrays.asList(b1, b2));
//
//        System.out.println("✔ Books sample data inserted.");
//    }
//
//    // MEMBERS = standalone collection
//    static void seedMembers(MongoCollection<Document> members) {
//
//        if (members.countDocuments() > 0) return;
//
//        Document m1 = new Document()
//                .append("full_name", "Nariman Suleimenov")
//                .append("email", "nariman@mail.com")
//                .append("phone", "+77000000001")
//                .append("status", "Active")
//                .append("joined_date", new Date());
//
//        Document m2 = new Document()
//                .append("full_name", "Ali Bek")
//                .append("email", "ali@mail.com")
//                .append("phone", "+77000000002")
//                .append("status", "Active")
//                .append("joined_date", new Date());
//
//        members.insertMany(Arrays.asList(m1, m2));
//
//        System.out.println("✔ Members sample data inserted.");
//    }
//
//    // LOANS = REFERENCES member_email + isbn
//    static void seedLoans(MongoCollection<Document> loans) {
//
//        if (loans.countDocuments() > 0) return;
//
//        Document l1 = new Document()
//                .append("member_email", "nariman@mail.com")
//                .append("book_isbn", "ISBN001")
//                .append("loan_date", new Date())
//                .append("fine", 0);
//
//        Document l2 = new Document()
//                .append("member_email", "ali@mail.com")
//                .append("book_isbn", "ISBN002")
//                .append("loan_date", new Date())
//                .append("fine", 10);
//
//        loans.insertMany(Arrays.asList(l1, l2));
//
//        System.out.println("✔ Loans sample data inserted.\n");
//    }
//
//    // =====================================================
//    // TASK 4 - CRUD ON MEMBERS
//    // =====================================================
//
//    // CREATE
//    static void insertMember(MongoCollection<Document> members) {
//
//        members.deleteMany(Filters.eq("email", "mongo@mail.com"));
//
//        Document doc = new Document()
//                .append("full_name", "Mongo User")
//                .append("email", "mongo@mail.com")
//                .append("phone", "+77000009999")
//                .append("status", "Active")
//                .append("joined_date", new Date());
//
//        members.insertOne(doc);
//
//        System.out.println("✔ INSERT completed.\n");
//    }
//
//    // READ
//    static void readMembers(MongoCollection<Document> members) {
//
//        System.out.println("📄 MEMBERS:");
//
//        FindIterable<Document> docs =
//                members.find().sort(Sorts.ascending("full_name"));
//
//        for (Document doc : docs) {
//            System.out.printf(
//                    "%-22s | %-25s | %s%n",
//                    doc.getString("full_name"),
//                    doc.getString("email"),
//                    doc.getString("status")
//            );
//        }
//
//        System.out.println("---------------------------------------\n");
//    }
//
//    // UPDATE
//    static void updateMember(MongoCollection<Document> members) {
//
//        UpdateResult result = members.updateOne(
//                Filters.eq("email", "mongo@mail.com"),
//                Updates.set("status", "Suspended")
//        );
//
//        System.out.println("✔ UPDATE completed. Modified: "
//                + result.getModifiedCount() + "\n");
//    }
//
//    // DELETE
//    static void deleteMember(MongoCollection<Document> members) {
//
//        DeleteResult result = members.deleteOne(
//                Filters.eq("email", "mongo@mail.com")
//        );
//
//        System.out.println("✔ DELETE completed. Deleted: "
//                + result.getDeletedCount() + "\n");
//    }
//}



//package  org.example;
//import com.mongodb.client.*;
//import org.bson.Document;
//
//import static com.mongodb.client.model.Filters.eq;
//
//public class Main {
//    public static void main(String[] args) {
//        try (MongoClient client = MongoClients.create("mongodb+srv://narik:Nariman17@cluster0.nhpbper.mongodb.net/?retryWrites=true&w=majority")) {
//            MongoDatabase db = client.getDatabase("library_db");
//            MongoCollection<Document> collection = db.getCollection("members");
//
//            System.out.println("Connected to MongoDB!");
//
//            insert(collection);
//            read(collection);
//            update(collection);
//            delete(collection);
//
//        } catch (Exception e) {
//            System.err.println("Ошибка подключения или работы:");
//            e.printStackTrace(); // Это покажет точную причину в консоли
//        }
//        }
//
//        // CREATE
//        static void insert(MongoCollection<Document> collection) {
//            Document doc = new Document("name", "Mongo User")
//                    .append("email", "mongo@mail.com")
//                    .append("status", "Active");
//
//            collection.insertOne(doc);
//            System.out.println("Inserted!");
//        }
//
//        // READ
//        static void read(MongoCollection<Document> collection) {
//            System.out.println("Documents:");
//
//            FindIterable<Document> docs = collection.find();
//
//            for (Document doc : docs) {
//                System.out.println(doc.toJson());
//            }
//        }
//
//        // UPDATE
//        static void update(MongoCollection<Document> collection) {
//            collection.updateOne(
//                    eq("email", "mongo@mail.com"),
//                    new Document("$set", new Document("status", "Suspended"))
//            );
//
//            System.out.println("Updated!");
//        }
//
//        // DELETE
//        static void delete(MongoCollection<Document> collection) {
//            collection.deleteOne(eq("email", "mongo@mail.com"));
//            System.out.println("Deleted!");
//        }
//    }


//package org.example;
//
//import com.mongodb.client.*;
//import com.mongodb.client.model.Filters;
//import com.mongodb.client.model.Sorts;
//import com.mongodb.client.model.Updates;
//import com.mongodb.client.result.DeleteResult;
//import com.mongodb.client.result.UpdateResult;
//import org.bson.Document;
//
//import java.util.Arrays;
//import java.util.Date;
//
//public class Main {
//
//    static final String CONNECTION_STRING =
//            "mongodb+srv://narik:Nariman17@cluster0.nhpbper.mongodb.net/" +
//                    "?retryWrites=true&w=majority";
//
//    public static void main(String[] args) {
//        try (MongoClient client = MongoClients.create(CONNECTION_STRING)) {
//
//            MongoDatabase db = client.getDatabase("library_db");
//
//            MongoCollection<Document> books   = db.getCollection("books");
//            MongoCollection<Document> members = db.getCollection("members");
//
//            System.out.println("Connected to MongoDB!\n");
//
//            // ── BOOKS CRUD ─────────────────────────────────────────
//            System.out.println("===== BOOKS =====");
//            insertBook(books);
//            readBooks(books);
//            updateBook(books);
//            deleteBook(books);
//            System.out.println("Books after delete:");
//            readBooks(books);
//
//            // ── MEMBERS CRUD ───────────────────────────────────────
//            System.out.println("\n===== MEMBERS =====");
//            insertMember(members);
//            readMembers(members);
//            updateMember(members);
//            deleteMember(members);
//            System.out.println("Members after delete:");
//            readMembers(members);
//
//        } catch (Exception e) {
//            System.err.println("Error: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    // ══════════════════════════════════════════════════════════
//    //  BOOKS CRUD
//    // ══════════════════════════════════════════════════════════
//
//    // CREATE – insert a new book with embedded authors & categories
//    static void insertBook(MongoCollection<Document> books) {
//        // Remove if already exists so re-runs stay clean
//        books.deleteMany(Filters.eq("isbn", "ISBN003"));
//
//        Document book = new Document()
//                .append("title",        "Introduction to Algorithms")
//                .append("isbn",         "ISBN003")
//                .append("publication_year", 2009)
//                .append("available",    true)
//                .append("authors", Arrays.asList(              // EMBEDDED authors
//                        new Document("name", "Thomas H. Cormen")
//                                .append("nationality", "American"),
//                        new Document("name", "Charles E. Leiserson")
//                                .append("nationality", "American")
//                ))
//                .append("categories", Arrays.asList("Algorithms", "Computer Science")); // EMBEDDED
//
//        books.insertOne(book);
//        System.out.println("[INSERT] Book added: " + book.getString("title"));
//    }
//
//    // READ – print all books sorted by title
//    static void readBooks(MongoCollection<Document> books) {
//        System.out.println("[SELECT] All books:");
//        for (Document doc : books.find().sort(Sorts.ascending("title"))) {
//            System.out.println("  Title    : " + doc.getString("title"));
//            System.out.println("  ISBN     : " + doc.getString("isbn"));
//            System.out.println("  Year     : " + doc.getInteger("publication_year"));
//            System.out.println("  Available: " + doc.getBoolean("available"));
//            System.out.println("  ---");
//        }
//    }
//
//    // UPDATE – mark ISBN003 as unavailable
//    static void updateBook(MongoCollection<Document> books) {
//        UpdateResult result = books.updateOne(
//                Filters.eq("isbn", "ISBN003"),
//                Updates.set("available", false)
//        );
//        System.out.println("[UPDATE] Books modified: " + result.getModifiedCount());
//    }
//
//    // DELETE – remove ISBN003
//    static void deleteBook(MongoCollection<Document> books) {
//        DeleteResult result = books.deleteOne(Filters.eq("isbn", "ISBN003"));
//        System.out.println("[DELETE] Books removed: " + result.getDeletedCount());
//    }
//
//    // ══════════════════════════════════════════════════════════
//    //  MEMBERS CRUD
//    // ══════════════════════════════════════════════════════════
//
//    // CREATE – insert a new member
//    static void insertMember(MongoCollection<Document> members) {
//        members.deleteMany(Filters.eq("email", "mongo@mail.com"));
//
//        Document doc = new Document()
//                .append("full_name",   "Mongo User")
//                .append("email",       "mongo@mail.com")
//                .append("phone",       "+7-700-000-0001")
//                .append("joined_date", new Date())
//                .append("status",      "Active");
//
//        members.insertOne(doc);
//        System.out.println("[INSERT] Member added: " + doc.getString("full_name"));
//    }
//
//    // READ – print all members
//    static void readMembers(MongoCollection<Document> members) {
//        System.out.println("[SELECT] All members:");
//        for (Document doc : members.find().sort(Sorts.ascending("full_name"))) {
//            System.out.printf("  %-20s | %-25s | %s%n",
//                    doc.getString("full_name"),
//                    doc.getString("email"),
//                    doc.getString("status"));
//        }
//    }
//
//    // UPDATE – suspend the member
//    static void updateMember(MongoCollection<Document> members) {
//        UpdateResult result = members.updateOne(
//                Filters.eq("email", "mongo@mail.com"),
//                Updates.set("status", "Suspended")
//        );
//        System.out.println("[UPDATE] Members modified: " + result.getModifiedCount());
//    }
//
//    // DELETE – remove the member
//    static void deleteMember(MongoCollection<Document> members) {
//        DeleteResult result = members.deleteOne(Filters.eq("email", "mongo@mail.com"));
//        System.out.println("[DELETE] Members removed: " + result.getDeletedCount());
//    }
//}


package org.example;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import org.bson.Document;

import java.util.Arrays;

public class Main {

    static final String CONNECTION =
            "mongodb+srv://narik:Nariman17@cluster0.nhpbper.mongodb.net/?retryWrites=true&w=majority";

    public static void main(String[] args) {

        try (MongoClient client = MongoClients.create(CONNECTION)) {

            MongoDatabase db = client.getDatabase("cinema_db");
            MongoCollection<Document> staff = db.getCollection("staff");

            System.out.println("Connected to MongoDB Atlas");
            System.out.println("Database: cinema_db\n");

            // Seed initial data (если пусто)
            seedStaff(staff);

            System.out.println("========== CRUD ON STAFF ==========\n");

            insertStaff(staff);
            readStaff(staff);

            updateStaff(staff);
            readStaff(staff);

            deleteStaff(staff);
            readStaff(staff);

            System.out.println("Program finished successfully.");

        } catch (Exception e) {
            System.out.println("Error:");
            e.printStackTrace();
        }
    }

    // ============================================
    // SEED (initial data)
    // ============================================
    static void seedStaff(MongoCollection<Document> staff) {

        if (staff.countDocuments() > 0) return;

        Document s1 = new Document()
                .append("id", 1)
                .append("full_name", "John Manager")
                .append("position", "Manager")
                .append("supervisor_id", null);

        Document s2 = new Document()
                .append("id", 2)
                .append("full_name", "Sara Worker")
                .append("position", "Cashier")
                .append("supervisor_id", 1);

        Document s3 = new Document()
                .append("id", 3)
                .append("full_name", "Mike Worker")
                .append("position", "Cleaner")
                .append("supervisor_id", 1);

        staff.insertMany(Arrays.asList(s1, s2, s3));

        System.out.println("✔ Staff sample data inserted.\n");
    }

    // ============================================
    // CREATE
    // ============================================
    static void insertStaff(MongoCollection<Document> staff) {

        // чтобы не было дублей при повторном запуске
        staff.deleteMany(Filters.eq("id", 99));

        Document doc = new Document()
                .append("id", 99)
                .append("full_name", "Karim Islambekov")
                .append("position", "Assistant")
                .append("supervisor_id", 1);

        staff.insertOne(doc);

        System.out.println("✔ INSERT (staff) completed.\n");
    }

    // ============================================
    // READ
    // ============================================
    static void readStaff(MongoCollection<Document> staff) {

        System.out.println("📄 STAFF LIST:");

        FindIterable<Document> docs =
                staff.find().sort(Sorts.ascending("id"));

        for (Document doc : docs) {
            System.out.printf(
                    "ID: %-3d | %-20s | %-10s | Supervisor: %s%n",
                    doc.getInteger("id"),
                    doc.getString("full_name"),
                    doc.getString("position"),
                    doc.get("supervisor_id")
            );
        }

        System.out.println("----------------------------------------\n");
    }

    // ============================================
    // UPDATE
    // ============================================
    static void updateStaff(MongoCollection<Document> staff) {

        UpdateResult result = staff.updateOne(
                Filters.eq("id", 99),
                Updates.set("position", "Senior Assistant")
        );

        System.out.println("✔ UPDATE completed. Modified: "
                + result.getModifiedCount() + "\n");
    }

    // ============================================
    // DELETE
    // ============================================
    static void deleteStaff(MongoCollection<Document> staff) {

        DeleteResult result = staff.deleteOne(
                Filters.eq("id", 99)
        );

        System.out.println("✔ DELETE completed. Deleted: "
                + result.getDeletedCount() + "\n");
    }
}