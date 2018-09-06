INSERT INTO `user` VALUES (1,'stelmaszak@gmail.com','$2a$10$w4QZCx1xLx9sX8hZ6kPqrujqOW7TdL7OJIyF6bLTTnZd/0AR.9N1C',2945,'Stelmach'),(2,'michal@gmail.com','$2a$10$2ZZXjJssKNggJ65ib8wBN.JNVF9E4opZkregZUo7tHiSdkVPazFLm',623,'Pleb'),(3,'m@gmail.com','$2a$10$2ZZXjJssKNggJ65ib8wBN.JNVF9E4opZkregZUo7tHiSdkVPazFLm',95,'Kogut'),(4,'s@gmail.com','$2a$10$2ZZXjJssKNggJ65ib8wBN.JNVF9E4opZkregZUo7tHiSdkVPazFLm',934,'Alek'),(5,'r@gmail.com','$2a$10$2ZZXjJssKNggJ65ib8wBN.JNVF9E4opZkregZUo7tHiSdkVPazFLm',345,'Rich');
INSERT INTO `user_roles` VALUES (1,'USER'),(1,'ADMIN'),(2,'USER'),(3,'ADMIN'),(4,'ADMIN'),(5,'ADMIN');
INSERT INTO `category` VALUES (1,'News'),(2,'Gaming'),(3,'Videos'),(4,'Funny'),(5,'UFC'),(6,'Politics'),(7,'Music'),(8,'Science'),(9,'NBA'),(10,'Pics'),(11,'Tattoos'),(12,'Memes');
INSERT INTO `message` VALUES (1, now(), 0, 'To jest pierwsza wiadomość', 'Tytuł 1', 1, 2), (2, now(), 0, 'To jest druga wiadomość', 'Tytuł 2', 1, 2), (3, now(), 0, 'To jest trzecia wiadomość', 'Tytuł 3', 2, 1), (4, now(), 0, 'To jest czwarta wiadomość', 'Tytuł 4', 2, 1);
INSERT INTO `post` VALUES (1, now(), 1562, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Leo in vitae turpis massa sed elementum. Nulla aliquet enim tortor at auctor urna nunc id. Feugiat scelerisque varius morbi enim nunc faucibus. Massa placerat duis ultricies lacus sed turpis. Aliquet nibh praesent tristique magna sit amet. Ullamcorper eget nulla facilisi etiam dignissim diam. Tristique nulla aliquet enim tortor at. Massa sapien faucibus et molestie ac. Sit amet nisl purus in mollis nunc. Cursus vitae congue mauris rhoncus aenean vel elit. Maecenas pharetra convallis posuere morbi leo urna.', 'Lorem ipsum dolor sit amet', null, 3, 1), (2, now(), 435, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Leo in vitae turpis massa sed elementum. Nulla aliquet enim tortor at auctor urna nunc id. Feugiat scelerisque varius morbi enim nunc faucibus. Massa placerat duis ultricies lacus sed turpis. Aliquet nibh praesent tristique magna sit amet. Ullamcorper eget nulla facilisi etiam dignissim diam. Tristique nulla aliquet enim tortor at. Massa sapien faucibus et molestie ac. Sit amet nisl purus in mollis nunc. Cursus vitae congue mauris rhoncus aenean vel elit. Maecenas pharetra convallis posuere morbi leo urna.', 'Lorem ipsum dolor sit amet', null, 2, 2), (3, now(), 343, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Leo in vitae turpis massa sed elementum. Nulla aliquet enim tortor at auctor urna nunc id. Feugiat scelerisque varius morbi enim nunc faucibus. Massa placerat duis ultricies lacus sed turpis. Aliquet nibh praesent tristique magna sit amet. Ullamcorper eget nulla facilisi etiam dignissim diam. Tristique nulla aliquet enim tortor at. Massa sapien faucibus et molestie ac. Sit amet nisl purus in mollis nunc. Cursus vitae congue mauris rhoncus aenean vel elit. Maecenas pharetra convallis posuere morbi leo urna.', 'Lorem ipsum dolor sit amet', null, 1, 1), (4, now(), 46, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Leo in vitae turpis massa sed elementum. Nulla aliquet enim tortor at auctor urna nunc id. Feugiat scelerisque varius morbi enim nunc faucibus. Massa placerat duis ultricies lacus sed turpis. Aliquet nibh praesent tristique magna sit amet. Ullamcorper eget nulla facilisi etiam dignissim diam. Tristique nulla aliquet enim tortor at. Massa sapien faucibus et molestie ac. Sit amet nisl purus in mollis nunc. Cursus vitae congue mauris rhoncus aenean vel elit. Maecenas pharetra convallis posuere morbi leo urna.', 'Lorem ipsum dolor sit amet', null, 4, 4), (5, now(), 5473, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Leo in vitae turpis massa sed elementum. Nulla aliquet enim tortor at auctor urna nunc id. Feugiat scelerisque varius morbi enim nunc faucibus. Massa placerat duis ultricies lacus sed turpis. Aliquet nibh praesent tristique magna sit amet. Ullamcorper eget nulla facilisi etiam dignissim diam. Tristique nulla aliquet enim tortor at. Massa sapien faucibus et molestie ac. Sit amet nisl purus in mollis nunc. Cursus vitae congue mauris rhoncus aenean vel elit. Maecenas pharetra convallis posuere morbi leo urna.', 'Lorem ipsum dolor sit amet', null, 2, 5), (6, now(), 12, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Leo in vitae turpis massa sed elementum. Nulla aliquet enim tortor at auctor urna nunc id. Feugiat scelerisque varius morbi enim nunc faucibus. Massa placerat duis ultricies lacus sed turpis. Aliquet nibh praesent tristique magna sit amet. Ullamcorper eget nulla facilisi etiam dignissim diam. Tristique nulla aliquet enim tortor at. Massa sapien faucibus et molestie ac. Sit amet nisl purus in mollis nunc. Cursus vitae congue mauris rhoncus aenean vel elit. Maecenas pharetra convallis posuere morbi leo urna.', 'Lorem ipsum dolor sit amet', null, 1, 5);