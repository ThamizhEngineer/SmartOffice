CREATE DEFINER=`so_user`@`%` PROCEDURE `smart_office`.`find_distance`(in fromLats float(10,6), in fromLongs float(10,6), in toLats float(10,6), in toLongs float(10,6), out resultCode VARCHAR(25))
BEGIN

DECLARE EXIT HANDLER FOR SQLEXCEPTION 
	BEGIN
	
	set resultCode='find_distance - Unknown Error';
	select resultCode;
	END;
if fromLats!=null or fromLats = '' or fromLongs!=null or fromLongs = '' or toLats!=null or toLats = '' or toLongs!=null or toLongs = '' THEN
set resultCode = 
  111.045 * DEGREES(ACOS(COS(RADIANS(fromLats))
 * COS(RADIANS(toLats))
 * COS(RADIANS(toLongs) - RADIANS(fromLongs))
 + SIN(RADIANS(fromLats))
 * SIN(RADIANS(toLats))));
select resultCode;
ELSE
set resultCode='0';
select resultCode;
 end if;
END
