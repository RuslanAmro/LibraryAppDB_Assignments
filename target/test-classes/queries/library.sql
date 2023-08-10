-- US01-1
select count(id)
from users; -- actual


select count(distinct id)
from users;
-- expected -- US01_AC01

-- US01-2
select *
from users;

-- ---------------

-- US02
select count(*)
from book_borrow
where is_returned = 0;

-- ---------------

-- US03
select name
from book_categories;

-- ---------------

-- US04
select *
from books
where name = 'Clean Code';

-- --------------

-- US05
select bc.name, count(*)
from book_borrow bb
         inner join books b on bb.book_id = b.id
         inner join book_categories bc on b.book_category_id = bc.id
group by name
order by 2 desc;





