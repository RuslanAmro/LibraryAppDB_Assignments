select count(id)
from users; -- actual

select count(distinct id)
from users; -- expected -- US01_AC01

select *
from users; -- US01_AC02

---------------

select count(*)
from book_borrow
where is_returned = 0; -- US02

---------------




