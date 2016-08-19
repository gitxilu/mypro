drop table if exists user;
create table if not exists user(
    userid int primary key auto_increment,
    username varchar(70) not null,
    password varchar(255) not null,
    gender varchar(33) not null,
    email varchar(70) not null
)
insert into user values(null,'曦露','53246E9DE5461446DB2F9F84E9A36E10','女','xilu@163.com');#xilu201217
insert into user values(null,'yuxin','8C5CB4A8E57A127B3E895F2BB3E7308F','女','yuxin@163.com');#yuxin
insert into user values(null,'xilu','52E35E3C29FE42DD7BB3BD39C82EA60E','女','xilu@163.com');#201217

drop table if exists admin;
create table if not exists admin(
    adminid int primary key auto_increment,
    name varchar(20) not null
)
insert into admin values(null,'沈天昊');
insert into admin values(null,'陆司辰');
insert into admin values(null,'莫艺洵');


drop table if exists type;
create table if not exists type(
    typeid int primary key auto_increment,
    name varchar(20) not null,
    imagepath varchar(70) not null,
    click int not null default 0, 
    adminid int,
    constraint adminid_FK foreign key(adminid) references admin(adminid)
)
insert into type values(null,'汽车','../images/car.jpg',0,1);
insert into type values(null,'电脑','../images/computer.jpg',0,1);
insert into type values(null,'游戏','../images/game.jpg',0,2);
insert into type values(null,'房子','../images/house.jpg',0,2);
insert into type values(null,'手机','../images/phone.jpg',0,3);


drop table if exists topic;
create table if not exists topic(
     topicid int primary key auto_increment,
     name varchar(70) not null,
     author varchar(70) not null,
     content varchar(255) not null,
     time timestamp not null,
     typeid int,
     constraint typeid_FK foreign key(typeid) references type(typeid)
)
insert into topic(name,author,content,typeid) values('iPhone6收到了么','陆司辰','其实也不咋地，让洛逸帆给你设计一款吧',1);
insert into topic(name,author,content,typeid) values('别墅好空旷啊','沈天昊','你们干嘛都那么忙，这么大房子就我一个人',1);
insert into topic(name,author,content,typeid) values('这跑车真实你设计的','熙墨','难怪有人把它当成出租车',2);
insert into topic(name,author,content,typeid) values('这个界面怎么样','雨欣','它可以嫁接在电脑和手机上，不过还需要一个接口程序',2);
insert into topic(name,author,content,typeid) values('这游戏太弱智了','沈天昊','不过对你来说确实有些深奥，多跟哥学学',3);
insert into topic(name,author,content,typeid) values('电脑已经装好了','洛逸帆','加了这么多反黑客软件还是被攻击，到底是黑客太强还是.....',3);
insert into topic(name,author,content,typeid) values('能不能把手机还我','莫艺洵','那里面的东西对我真的很重要',4);
insert into topic(name,author,content,typeid) values('这栋房子已经设计好了','熙墨','能不能让我知道这是为谁设计的，我保证告诉其他人',4);
insert into topic(name,author,content,typeid) values('幸亏没开我赢得那辆车','陆司辰','我真怕哪天有人也拦我的车，还要给我双倍的车费',5);
insert into topic(name,author,content,typeid) values('图我已经画好了','莫艺洵','你是要把它当前台么',5);

drop table if exists reply;
create table if not exists reply(
    replyid int primary key auto_increment,
    name varchar(70) not null,
    author varchar(70) not null,
    content varchar(255) not null,
    time timestamp not null,
    topicid int,
    constraint replyid_FK foreign key(topicid) references topic(topicid)
)
insert into reply(name,author,content,topicid) values('欢迎来思辰之家','陆司辰','我给你经理的工资，这里绝对比别墅好玩',2);
insert into reply(name,author,content,topicid) values('你们以后别想开我的车','沈天昊','以后谁再敢说出租车三字，我——我就把他扔到我车顶上在赛道上跑几圈',9);
insert into reply(name,author,content,topicid) values('画的不错','洛逸帆','回头我让她给我做成网站页面',10);
insert into reply(name,author,content,topicid) values('我只是看这个游戏的场景','莫艺洵','你不觉得这些画面很美么，尤其是这线条——',5);
insert into reply(name,author,content,topicid) values('不要告诉我你又准备把它当餐厅','熙墨','你给我看清楚这是大厦，你真的拿他当餐厅？顾客吃完下去还不够消化的',2);

delete from reply where topicid=6;


drop table if exists address;
create table if not exists address(
    addressid int primary key auto_increment,
    ip varchar(20) not null,
    location varchar(20) not null
)
insert into address values(null,'www.baidu.com','北京');
insert into address values(null,'www.bing.com','东京');
insert into address values(null,'http://open163.com','大阪');
insert into address values(null,'218.198.176.91','新郑');



drop table if exists flow;
create table if not exists flow(
   flowid int primary key auto_increment,
   historydate date not null,
   count int not null default 200
)
insert into flow(historydate) values(now() - INTERVAL 1 DAY);
