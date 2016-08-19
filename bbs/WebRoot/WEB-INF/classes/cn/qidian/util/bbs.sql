drop table if exists user;
create table if not exists user(
    userid int primary key auto_increment,
    username varchar(70) not null,
    password varchar(255) not null,
    gender varchar(33) not null,
    email varchar(70) not null
)
insert into user values(null,'��¶','53246E9DE5461446DB2F9F84E9A36E10','Ů','xilu@163.com');#xilu201217
insert into user values(null,'yuxin','8C5CB4A8E57A127B3E895F2BB3E7308F','Ů','yuxin@163.com');#yuxin
insert into user values(null,'xilu','52E35E3C29FE42DD7BB3BD39C82EA60E','Ů','xilu@163.com');#201217

drop table if exists admin;
create table if not exists admin(
    adminid int primary key auto_increment,
    name varchar(20) not null
)
insert into admin values(null,'�����');
insert into admin values(null,'½˾��');
insert into admin values(null,'Ī���');


drop table if exists type;
create table if not exists type(
    typeid int primary key auto_increment,
    name varchar(20) not null,
    imagepath varchar(70) not null,
    click int not null default 0, 
    adminid int,
    constraint adminid_FK foreign key(adminid) references admin(adminid)
)
insert into type values(null,'����','../images/car.jpg',0,1);
insert into type values(null,'����','../images/computer.jpg',0,1);
insert into type values(null,'��Ϸ','../images/game.jpg',0,2);
insert into type values(null,'����','../images/house.jpg',0,2);
insert into type values(null,'�ֻ�','../images/phone.jpg',0,3);


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
insert into topic(name,author,content,typeid) values('iPhone6�յ���ô','½˾��','��ʵҲ��զ�أ������ݷ��������һ���',1);
insert into topic(name,author,content,typeid) values('�����ÿտ���','�����','���Ǹ��ﶼ��ôæ����ô���Ӿ���һ����',1);
insert into topic(name,author,content,typeid) values('���ܳ���ʵ����Ƶ�','��ī','�ѹ����˰������ɳ��⳵',2);
insert into topic(name,author,content,typeid) values('���������ô��','����','�����Լ޽��ڵ��Ժ��ֻ��ϣ���������Ҫһ���ӿڳ���',2);
insert into topic(name,author,content,typeid) values('����Ϸ̫������','�����','����������˵ȷʵ��Щ��£������ѧѧ',3);
insert into topic(name,author,content,typeid) values('�����Ѿ�װ����','���ݷ�','������ô�෴�ڿ�������Ǳ������������Ǻڿ�̫ǿ����.....',3);
insert into topic(name,author,content,typeid) values('�ܲ��ܰ��ֻ�����','Ī���','������Ķ���������ĺ���Ҫ',4);
insert into topic(name,author,content,typeid) values('�ⶰ�����Ѿ���ƺ���','��ī','�ܲ�������֪������Ϊ˭��Ƶģ��ұ�֤����������',4);
insert into topic(name,author,content,typeid) values('�ҿ�û����Ӯ��������','½˾��','��������������Ҳ���ҵĳ�����Ҫ����˫���ĳ���',5);
insert into topic(name,author,content,typeid) values('ͼ���Ѿ�������','Ī���','����Ҫ������ǰ̨ô',5);

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
insert into reply(name,author,content,topicid) values('��ӭ��˼��֮��','½˾��','�Ҹ��㾭��Ĺ��ʣ�������Աȱ�������',2);
insert into reply(name,author,content,topicid) values('�����Ժ���뿪�ҵĳ�','�����','�Ժ�˭�ٸ�˵���⳵���֣��ҡ����ҾͰ����ӵ��ҳ��������������ܼ�Ȧ',9);
insert into reply(name,author,content,topicid) values('���Ĳ���','���ݷ�','��ͷ����������������վҳ��',10);
insert into reply(name,author,content,topicid) values('��ֻ�ǿ������Ϸ�ĳ���','Ī���','�㲻������Щ�������ô������������������',5);
insert into reply(name,author,content,topicid) values('��Ҫ����������׼������������','��ī','����ҿ�������Ǵ��ã�������������������˿ͳ�����ȥ������������',2);

delete from reply where topicid=6;


drop table if exists address;
create table if not exists address(
    addressid int primary key auto_increment,
    ip varchar(20) not null,
    location varchar(20) not null
)
insert into address values(null,'www.baidu.com','����');
insert into address values(null,'www.bing.com','����');
insert into address values(null,'http://open163.com','����');
insert into address values(null,'218.198.176.91','��֣');



drop table if exists flow;
create table if not exists flow(
   flowid int primary key auto_increment,
   historydate date not null,
   count int not null default 200
)
insert into flow(historydate) values(now() - INTERVAL 1 DAY);
