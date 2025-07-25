
CREATE TABLE tblUsers (
    userID INT PRIMARY KEY IDENTITY,
    username NVARCHAR(50) UNIQUE,
    password NVARCHAR(100),
    email NVARCHAR(100),
    role NVARCHAR(10) DEFAULT 'user' -- 'admin' or 'user'
);

CREATE TABLE tblCategories (
    categoryID INT PRIMARY KEY IDENTITY,
    categoryName NVARCHAR(100)
);

CREATE TABLE tblGames (
    gameID INT PRIMARY KEY IDENTITY,
    title NVARCHAR(200),
    description TEXT,
    imageURL NVARCHAR(500),
    fileURL NVARCHAR(500),
    categoryID INT FOREIGN KEY REFERENCES tblCategories(categoryID),
    uploadDate DATETIME DEFAULT GETDATE()
);

CREATE TABLE tblComments (
    commentID INT PRIMARY KEY IDENTITY,
    gameID INT FOREIGN KEY REFERENCES tblGames(gameID),
    userID INT FOREIGN KEY REFERENCES tblUsers(userID),
    commentText NVARCHAR(1000),
    commentDate DATETIME DEFAULT GETDATE(),
    status NVARCHAR(10) DEFAULT 'pending' -- 'pending', 'approved', 'rejected'
);
INSERT INTO tblUsers (username, password, email, role) VALUES
('admin_user', 'adminpass123', 'admin@example.com', 'admin'),
('john_doe', 'johnpass', 'john.doe@example.com', 'user'),
('jane_smith', 'janepass', 'jane.smith@example.com', 'user');
INSERT INTO tblCategories (categoryName) VALUES
('Action'),
('Adventure'),
('Puzzle'),
('Strategy'),
('Simulation');
-- Assuming Category IDs: 1=Action, 2=Adventure, 3=Puzzle, 4=Strategy, 5=Simulation
INSERT INTO tblGames (title, description, imageURL, fileURL, categoryID) VALUES
('Space Explorer', 'An epic space adventure game.', 'https://igg-games.com/wp-content/uploads/2018/06/Space-Explorers-Free-Download.jpg', 'https://megaup.net/d87v/Space.Explorers.rar', 2),
('SuperTaxCity', 'Build and manage your dream city.', 'https://igg-games.com/wp-content/uploads/2025/06/SuperTaxCity-Free-Download.jpg', 'https://megaup.net/8f72a80114781f347c51bd80b68fefc3/SuperTaxCity.v1.1.4.rar', 5),
('Stellaris', 'get ready to explore, discover and interact with a multitude of species as you journey among the stars. Forge a galactic empire by sending out science ships to survey and explore, while construction ships build stations around newly discovered planets. Discover buried treasures and galactic wonders as you spin a direction for your society, creating limitations and evolutions for your explorers. Alliances will form and wars will be declared.

Like all our Grand Strategy games, the adventure evolves with time. Because free updates are a part of any active Paradox game, you can continue to grow and expand your empire with new technologies and capabilities. What will you find beyond the stars? Only you can answer that.', 'https://igg-games.com/wp-content/uploads/2025/06/Stellaris-Free-Download.jpg', 'https://megaup.net/e56923db4a62d0c894628256d8befd29/Stellaris.v4.0.20.rar', 3),
('Heroic Adventures', 'A classic fantasy action RPG.', 'https://igg-games.com/wp-content/uploads/2020/12/Heroic-Adventures-Free-Download.jpg', 'http://example.com/games/heroic_quest.zip', 1);
-- Assuming:
-- User IDs: 1=admin_user, 2=john_doe, 3=jane_smith
-- Game IDs: 1=Space Explorer, 2=City Builder Tycoon, 3=Mystic Maze, 4=Heroic Quest

INSERT INTO tblComments (gameID, userID, commentText, status) VALUES
(1, 2, 'This game is amazing! Highly recommend.', 'approved'),
(3, 3, 'A bit challenging, but very fun.', 'approved'),
(1, 1, 'Great addition to the platform!', 'approved'),
(2, 2, 'The graphics are stunning!', 'pending'),
(4, 3, 'Enjoying the combat system.', 'approved');