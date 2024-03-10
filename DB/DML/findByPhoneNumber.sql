CREATE PROCEDURE findByPhoneNumber
	@PhoneNumber VARCHAR(45),
    @Success INT OUTPUT
AS
BEGIN
    SET @Success = 0; 

    BEGIN TRY
        SELECT @Success = count(*)
		FROM library_user AS u
		WHERE @PhoneNumber =  u.phone_number

         
    END TRY
    BEGIN CATCH
       
        
    END CATCH
END