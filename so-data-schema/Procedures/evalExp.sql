DROP PROCEDURE IF EXISTS `smart_office`.`evalExp`;
CREATE DEFINER=`so_user`@`%` PROCEDURE `smart_office`.`evalExp`(IN formulaStr varchar(100),OUT expResult VARCHAR(100))
BEGIN 
	
	set @out1 = '';
	SET @tempSql= CONCAT('SELECT ', formulaStr, ' into @out1 from dual');
 
 	PREPARE StmtStd FROM @tempSql;
	EXECUTE StmtStd;
	DEALLOCATE PREPARE StmtStd;
  	set expResult = @out1;

END