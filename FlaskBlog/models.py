from datetime import datetime
from FlaskBlog import db, login_manager
from flask_login import UserMixin

@login_manager.user_loader
def load_user(user_id):
    return User.query.get(int(user_id))

# Define the User class, which represents users in the database
class User(db.Model, UserMixin):
    id = db.Column(db.Integer, primary_key=True)  # Define the user's unique identifier as an Integer
    username = db.Column(db.String(20), unique=True, nullable=False)  # User's username, limited to 20 characters
    email = db.Column(db.String(120), unique=True, nullable=False)  # User's email, limited to 120 characters
    image_file = db.Column(db.String(20), nullable=False, default='default.jpg')  # User's profile image file
    password = db.Column(db.String(60), nullable=False)  # User's password, hashed and stored securely
    posts = db.relationship('Post', backref='author', lazy=True)  # Establish a one-to-many relationship with Post model

    def __repr__(self):
        return f"User('{self.username}', '{self.email}', '{self.image_file}')"

# Define the Post class, which represents blog posts in the database
class Post(db.Model):
    id = db.Column(db.Integer, primary_key=True)  # Define the post's unique identifier as an Integer
    title = db.Column(db.String(100), nullable=False)  # Post title, limited to 100 characters
    date_posted = db.Column(db.DateTime, nullable=False, default=datetime.utcnow)  # Date the post was created
    content = db.Column(db.Text, nullable=False)  # Post content in text format
    user_id = db.Column(db.Integer, db.ForeignKey('user.id'), nullable=False)  # Foreign key linking posts to users

    def __repr__(self):
        return f"User('{self.title}', '{self.date_posted}')"

# Import necessary modules and classes from other parts of the application
from FlaskBlog import db
from datetime import datetime
