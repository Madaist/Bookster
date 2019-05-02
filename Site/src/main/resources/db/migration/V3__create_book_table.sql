USE bookster;
GO
IF NOT EXISTS (SELECT * FROM sys.objects
                WHERE object_id = OBJECT_ID(N'[dbo].[book_tbl]')
                AND type IN (N'P', N'PC'))
    BEGIN
        EXEC dbo.sp_executesql @statement = N'CREATE TABLE book_tbl (
                id int NOT NULL IDENTITY(1, 1),
                title varchar(50) NOT NULL,
                author varchar(50),
                publishing_house varchar(50),
                year_of_release int, cantity int
                PRIMARY KEY (id),
            );';
    END
GO