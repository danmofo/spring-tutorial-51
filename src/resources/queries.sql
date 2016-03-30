select * from offer;

update offer
set username='dan2'
where username='dan';

select * from offer
inner join users
on offer.username = users.username;

select * from users;

delete from users where username = 'dan';

select last_insert_id();

select version() version;