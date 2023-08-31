from flask import *
from flask_sqlalchemy import SQLAlchemy
from forms import RegistrationForm, LoginForm
from datetime import datetime

app = Flask(__name__)

# Secret key for protecting forms against CSRF attacks
app.config['SECRET_KEY'] = '40bfaaf7cf15c237128ef5ae12c62e85'

# Configuration for connecting to the SQLite database
app.config['SQLAlCHEMY_DATABASE_URI'] = 'sqlite:///site.db'
db = SQLAlchemy(app)

# User class representing the 'User' table in the database
class User(db.Model):
    id = db.Column(db.String, primary_key=True)
    username = db.Column(db.String(20), unique=True, nullable=False)
    email = db.Column(db.String(120), unique=True, nullable=False)
    image_file = db.Column(db.String(20), nullable=False, default='default.jpg')
    password = db.Column(db.String(60), nullable=False)

    def __repr__(self):
        return f"User('{self.username}', '{self.email}', '{self.image_file}')"

# Post class representing the 'Post' table in the database
class Post(db.Model):
    id = db.Column(db.String, primary_key=True)
    title = db.Column(db.String(100), nullable=False)
    date_posted = db.Column(db.DateTime, nullable=False, default=datetime.utcnow)
    content = db.Column(db.Text, nullable=False)

# Sample post data
posts = [
    {
        'author': 'Asura Samsara',
        'title': 'Blog post 1',
        'content': 'Oonga Boonga',
        'date_posted': 'April 25, 2018'
    },
    {
        'author': 'Rogal Dorne',
        'title': 'Praetorian of Man',
        'content': 'Build walls',
        'date_posted': 'May 20, 2018'
    }
]

# Route for the home page
@app.route("/")
@app.route("/home")
def home():
    return render_template('home.html', posts=posts)

# Route for the about page
@app.route("/about")
def about():
    return render_template('about.html', title='About')

# Route for user registration
@app.route("/register", methods=['GET', 'POST'])
def register():
    form = RegistrationForm()
    if form.validate_on_submit():
        flash(f'Account created for {form.username.data}!', 'success')
        return redirect(url_for('home'))

    return render_template('register.html', title='Register', form=form)

# Route for user login
@app.route("/login", methods=['GET', 'POST'])
def login():
    form = LoginForm()
    if form.validate_on_submit():
        if form.email.data == 'admin@blog.com' and form.password.data == 'password':
            flash('Logged in successfully!', 'success')
            return redirect(url_for('home'))
        else:
            flash('Log in unsuccessful', 'danger')
    return render_template('login.html', title='Login', form=form)

if __name__ == '__main__':
    app.run(debug=True)  # Start the Flask application
