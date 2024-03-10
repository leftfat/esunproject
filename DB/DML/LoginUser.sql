CREATE PROCEDURE LoginUser
    @PhoneNumber VARCHAR(45),
    @Password VARCHAR(1000),
    @UserId INT OUTPUT,
    @UserName VARCHAR(45) OUTPUT,
	@LastLoginTime DATETIME OUTPUT
AS
BEGIN
    
    SELECT
	
	 @UserId = user_id,
	 @UserName = user_name,
	 @LastLoginTime = last_login_time
    FROM library_user
    WHERE phone_number = @PhoneNumber AND password = @Password
    
END