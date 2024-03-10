
--CREATE DATABASE Library

--USE Library;

CREATE TABLE "inventory"(
    "inventory_id" BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    "isbn" VARCHAR(13) NOT NULL,
    "store_time" DATETIME NOT NULL,
    "status" INT NOT NULL
);

CREATE TABLE "borrowing_record"(
    "record_id" BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
	"user_id" BIGINT NOT NULL,
    "inventory_id" BIGINT NOT NULL,
    "borrow_time" DATETIME NOT NULL,
    "return_time" DATETIME 
);
CREATE TABLE "book"(
    "isbn" VARCHAR(13) NOT NULL PRIMARY KEY,
    "book_name" NVARCHAR(100) NOT NULL,
    "author" NVARCHAR(50) NOT NULL,
    "introduction" NVARCHAR(500) 
);

CREATE TABLE "library_user"(
    "user_id" BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    "phone_number" VARCHAR(10) NOT NULL UNIQUE,
    "password" VARCHAR(1000) NOT NULL,
    "user_name" NVARCHAR(30) NOT NULL ,
    "registration_time" DATETIME NOT NULL DEFAULT 'new Date()',
    "last_login_time" DATETIME 
);

ALTER TABLE
    "borrowing_record" ADD CONSTRAINT "borrowing_record_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "library_user"("user_id");
ALTER TABLE
    "inventory" ADD CONSTRAINT "inventory_isbn_foreign" FOREIGN KEY("isbn") REFERENCES "book"("isbn");
ALTER TABLE
    "borrowing_record" ADD CONSTRAINT "borrowing_record_inventory_id_foreign" FOREIGN KEY("inventory_id") REFERENCES "inventory"("inventory_id");