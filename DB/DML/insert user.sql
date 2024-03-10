CREATE PROCEDURE InsertUser
    @PhoneNumber VARCHAR(45),
    @Password VARCHAR(1000),
    @UserName VARCHAR(45),
    @RegistrationTime DATETIME,
    @Success INT OUTPUT
AS
BEGIN
    SET @Success = 0; 

    BEGIN TRY
        INSERT INTO library_user(phone_number, password, user_name, registration_time)
        VALUES (@PhoneNumber, @Password, @UserName, @RegistrationTime);

        SET @Success = 1; 
    END TRY
    BEGIN CATCH
       
        SET @Success = 0; 
    END CATCH
END