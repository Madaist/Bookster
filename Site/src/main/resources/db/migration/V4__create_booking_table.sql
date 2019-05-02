USE bookster;
GO
IF NOT EXISTS (SELECT * FROM sys.objects
                WHERE object_id = OBJECT_ID(N'[dbo].[booking_tbl]')
                AND type IN (N'P', N'PC'))
    BEGIN
        EXEC dbo.sp_executesql @statement = N'CREATE TABLE booking_tbl (
                user_id int , book_id int
                PRIMARY KEY (user_id, book_id),
            );';
    END
GO