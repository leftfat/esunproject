CREATE PROCEDURE CheckRecords
    @userID BIGINT,
    @isbn varchar(13) OUTPUT,
    @inventoryID BIGINT OUTPUT,
    @bookName nvarchar(100) OUTPUT,
    @borrowTime DATETIME OUTPUT
    
AS
BEGIN
BEGIN TRY

SELECT @isbn = b.isbn,
       @inventoryID = i.inventory_id,
       @bookName = b.book_name,
       @borrowTime = br.borrow_time
  FROM borrowing_record br
  JOIN inventory i
    ON br.inventory_id=i.inventory_id
  JOIN book b
    ON i.isbn=b.isbn
 WHERE br.user_id=@userID


END TRY
BEGIN CATCH
END CATCH
END