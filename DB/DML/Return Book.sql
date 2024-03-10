CREATE PROCEDURE ReturnBook
    @user_id BIGINT,
    @isbn VARCHAR(13),
    @return_time DATETIME,
    @result BIGINT OUTPUT
AS
BEGIN
    BEGIN TRY
        SET @result = 0;
 
        --�ˬd�O�_�ɹL���ѥ���
        DECLARE @amount BIGINT;
        SELECT @amount=count(*) FROM inventory WHERE isbn = @isbn AND inventory_id IN (
            SELECT inventory_id FROM borrowing_record WHERE return_time IS NULL AND user_id=@user_id
        )
 
        IF @amount = 0
            RETURN
 
        DECLARE @inventory_id BIGINT;
        SELECT TOP 1 @inventory_id = inventory_id FROM inventory WHERE isbn = @isbn AND inventory_id IN (
            SELECT inventory_id FROM borrowing_record WHERE return_time IS NULL AND user_id=@user_id
        )
 
        IF @inventory_id IS NULL
            RETURN
 
        -- �w�s���A��s
        UPDATE inventory SET status=0 WHERE inventory_id=@inventory_id
 
        -- ��s�ɮѬ���(�ٮѮɶ�)
        UPDATE borrowing_record SET return_time=@return_time WHERE inventory_id=@inventory_id
   
        SET @result = @inventory_id; -- ���\

    END TRY
    BEGIN CATCH
        SET @result = 0; -- ����
    END CATCH
END;