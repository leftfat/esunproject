CREATE PROCEDURE BorrowBook
    @user_id BIGINT,
    @isbn VARCHAR(13),
    @borrow_time DATETIME,
    @result BIGINT OUTPUT
	
AS
BEGIN
    BEGIN TRY
        SET @result = 0;

        --檢查是否借過此書未還
        DECLARE @amount BIGINT;
        SELECT @amount=count(*)
		FROM inventory 
		WHERE isbn = @isbn AND inventory_id 
		IN (SELECT inventory_id FROM borrowing_record
                         WHERE return_time IS NULL  AND user_id = @user_id )
        IF @amount >0
            RETURN

        DECLARE @inventory_id BIGINT;
        SELECT TOP 1 @inventory_id = inventory_id FROM inventory WHERE isbn = @isbn AND status = 0;

        IF @inventory_id IS NULL
            RETURN

        -- 庫存狀態更新
        UPDATE inventory SET status = 1 WHERE inventory_id = @inventory_id;

        -- 新增借書紀錄
        INSERT INTO borrowing_record (user_id, inventory_id, borrow_time)
        VALUES (@user_id, @inventory_id, @borrow_time);
		
  
        SET @result =@inventory_id ; -- 成功

    END TRY
    BEGIN CATCH
        SET @result = 0; -- 失敗
    END CATCH
END;