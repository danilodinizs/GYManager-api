ALTER TABLE spreadsheet ALTER COLUMN created_at DROP DEFAULT;

ALTER TABLE spreadsheet 
ALTER COLUMN created_at TYPE DATE 
USING (
    CASE 
        WHEN created_at IS NULL THEN NULL 
        ELSE created_at::date 
    END
);
