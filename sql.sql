CREATE DATABASE QuizOnlineManagementt 
GO
USE QuizOnlineManagementt 
GO
CREATE TABLE tblRole
(
RoleID varchar(10) NOT NULL PRIMARY KEY,
RoleName nvarchar(50)
)
INSERT INTO tblRole(RoleID,RoleName) VALUES ('1','Admin')
INSERT INTO tblRole(RoleID,RoleName) VALUES ('2','Student')
CREATE TABLE tblRegistration
(
Email varchar(100) NOT NULL PRIMARY KEY,
Name nvarchar(100),
Password varchar(MAX),
RoleID varchar(10) REFERENCES tblRole(RoleID),
Status varchar(10),
)
INSERT INTO tblRegistration(Email,Name,Password,RoleID,Status) VALUES('nhatminh123@gmail.com','ADMINNNN','6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b',1,'Old')
CREATE TABLE tblSubject
(
SubjectID varchar(10) NOT NULL PRIMARY KEY,
SubjectName varchar(50),
SubjectContent varchar(MAX)
)
INSERT INTO tblSubject(SubjectID,SubjectName,SubjectContent) VALUES('MATH001','Math 1','This is the basic math for Kids')
INSERT INTO tblSubject(SubjectID,SubjectName,SubjectContent) VALUES('ENG001','English 1','This is the basic english for Kids')
CREATE TABLE tblQuestion
(
QuestionID varchar(30) NOT NULL PRIMARY KEY,
QuestionContent varchar(MAX),
CreateDate date,
SubjectID varchar(10) REFERENCES tblSubject (SubjectID),
Status bit
)
CREATE TABLE tblAnswer
(
AnswerID varchar(50) NOT NULL PRIMARY KEY,
AnswerContent varchar(MAX),
QuestionID varchar(30) REFERENCES tblQuestion (QuestionID),
Status bit
)
INSERT INTO tblQuestion(QuestionID,QuestionContent,CreateDate,SubjectID,Status) VALUES('Q-0000001','1 + 1 = ?','2021/04/18','MATH001','1')
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000001-1','1','Q-0000001',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000001-2','2','Q-0000001',1)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000001-3','3','Q-0000001',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000001-4','4','Q-0000001',0)
INSERT INTO tblQuestion(QuestionID,QuestionContent,CreateDate,SubjectID,Status) VALUES('Q-0000002','x + 1 = 2,x = ?','2021/04/18','MATH001','1')
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000002-1','1','Q-0000002',1)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000002-2','2','Q-0000002',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000002-3','3','Q-0000002',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000002-4','4','Q-0000002',0)
INSERT INTO tblQuestion(QuestionID,QuestionContent,CreateDate,SubjectID,Status) VALUES('Q-0000003','1 + 0 = ?','2021/04/18','MATH001','1')
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000003-1','1','Q-0000003',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000003-2','2','Q-0000003',1)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000003-3','3','Q-0000003',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000003-4','4','Q-0000003',0)
INSERT INTO tblQuestion(QuestionID,QuestionContent,CreateDate,SubjectID,Status) VALUES('Q-0000004','1 / 1 = ?','2021/04/18','MATH001','1')
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000004-1','1','Q-0000004',1)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000004-2','2','Q-0000004',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000004-3','3','Q-0000004',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000004-4','4','Q-0000004',0)
INSERT INTO tblQuestion(QuestionID,QuestionContent,CreateDate,SubjectID,Status) VALUES('Q-0000005','1 * 1 = ?','2021/04/18','MATH001','1')
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000005-1','1','Q-0000005',1)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000005-2','2','Q-0000005',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000005-3','3','Q-0000005',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000005-4','4','Q-0000005',0)
INSERT INTO tblQuestion(QuestionID,QuestionContent,CreateDate,SubjectID,Status) VALUES('Q-0000006','1 - 1 = ?','2021/04/18','MATH001','1')
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000006-1','0','Q-0000006',1)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000006-2','2','Q-0000006',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000006-3','3','Q-0000006',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000006-4','4','Q-0000006',0)
INSERT INTO tblQuestion(QuestionID,QuestionContent,CreateDate,SubjectID,Status) VALUES('Q-0000007','1 - 1 = ?','2021/04/18','MATH001','1')
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000007-1','0','Q-0000007',1)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000007-2','2','Q-0000007',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000007-3','3','Q-0000007',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000007-4','4','Q-0000007',0)
INSERT INTO tblQuestion(QuestionID,QuestionContent,CreateDate,SubjectID,Status) VALUES('Q-0000008','1 + 1 + 1= ?','2021/04/18','MATH001','1')
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000008-1','0','Q-0000008',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000008-2','2','Q-0000008',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000008-3','3','Q-0000008',1)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000008-4','4','Q-0000008',0)

INSERT INTO tblQuestion(QuestionID,QuestionContent,CreateDate,SubjectID,Status) VALUES('Q-0000009',N'Cat?','2021/04/18','ENG001','1')
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000009-1',N'Con meo','Q-0000009',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000009-2',N'Con mèo','Q-0000009',1)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000009-3',N'Con méo','Q-0000009',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000009-4',N'Con chó','Q-0000009',0)
INSERT INTO tblQuestion(QuestionID,QuestionContent,CreateDate,SubjectID,Status) VALUES('Q-0000010','Nani?','2021/04/18','ENG001','1')
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000010-1','Where','Q-0000010',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000010-2','Who','Q-0000010',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000010-3','What?????','Q-0000010',1)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000010-4','How','Q-0000010',0)
INSERT INTO tblQuestion(QuestionID,QuestionContent,CreateDate,SubjectID,Status) VALUES('Q-0000011','Pig in Southside???','2021/04/18','ENG001','1')
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000011-1','Lợn','Q-0000011',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000011-2','Nái','Q-0000011',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000011-3','Heo','Q-0000011',1)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000011-4','Pò','Q-0000011',0)
INSERT INTO tblQuestion(QuestionID,QuestionContent,CreateDate,SubjectID,Status) VALUES('Q-0000012','Chicken??','2021/04/18','ENG001','1')
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000012-1',N'Gà','Q-0000012',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000012-2','KFc','Q-0000012',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000012-3','MCDonald','Q-0000012',1)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000012-4','CUt','Q-0000012',0)
INSERT INTO tblQuestion(QuestionID,QuestionContent,CreateDate,SubjectID,Status) VALUES('Q-0000013','Love?','2021/04/18','ENG001','1')
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000013-1','SHit','Q-0000013',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000013-2','Dump','Q-0000013',0)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000013-3','Sukidesu','Q-0000013',1)
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('Q-0000013-4','Nani?????','Q-0000013',0)
CREATE TABLE tblHistory(
HistoryID varchar(100) PRIMARY KEY NOT NULL,
Email varchar(100) REFERENCES tblRegistration(Email),
SubjectID varchar(10) REFERENCES tblSubject(SubjectID),
Mark float,
createDate date,
)
CREATE TABLE tblHistoryDetail(
HistoryDetailID varchar(100) PRIMARY KEY NOT NULL,
HistoryID varchar(100) REFERENCES tblHistory(HistoryID),
QuestionID varchar(30) REFERENCES tblQuestion(QuestionID),
AnswerChoseID varchar(50) REFERENCES tblAnswer(AnswerID)
)

INSERT INTO tblSubject(SubjectID,SubjectName,SubjectContent) VALUES('','','')
INSERT INTO tblQuestion(QuestionID,QuestionContent,CreateDate,SubjectID) VALUES('','','','')
INSERT INTO tblAnswer(AnswerID,AnswerContent,QuestionID,Status) VALUES ('','','','')