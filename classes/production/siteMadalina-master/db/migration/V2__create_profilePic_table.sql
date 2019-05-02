USE bookster;
GO

IF NOT EXISTS (SELECT * FROM sys.objects
                WHERE object_id = OBJECT_ID(N'[dbo].[image_tbl]')
                AND type IN (N'P', N'PC'))
    BEGIN
        EXEC dbo.sp_executesql @statement = N'CREATE TABLE image_tbl (
                user_id int NOT NULL,
                profile_pic VARCHAR(MAX),
                PRIMARY KEY (user_id), FOREIGN KEY(user_id) REFERENCES user_tbl(id)
            );';
    END
GO