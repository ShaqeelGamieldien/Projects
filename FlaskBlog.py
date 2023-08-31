from flask import *
from flask_sqlalchemy import SQLAlchemy
from forms import RegistrationForm, LoginForm  # Make sure the import paths are correct
from datetime import datetime  # Correct the import of datetime

app = Flask(__name__)
app.config['SECRET_KEY'] = '40bfaaf7cf15c237128ef5ae12c62e85'
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///site.db'  # Correct the config key name
db = SQLAlchemy(app)

class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)  # Change db.String to db.Integer
    username = db.Column(db.String(20), unique=True, nullable=False)
    email = db.Column(db.String(120), unique=True, nullable=False)
    image_file = db.Column(db.String(20), nullable=False, default='default.jpg')
    password = db.Column(db.String(60), nullable=False)

    def __repr__(self):
        return f"User('{self.username}', '{self.email}', '{self.image_file}')"

class Post(db.Model):
    id = db.Column(db.Integer, primary_key=True)  # Change db.String to db.Integer
    title = db.Column(db.String(100), nullable=False)
    date_posted = db.Column(db.DateTime, nullable=False, default=datetime.utcnow)
    content = db.Column(db.Text, nullable=False)

posts = [
    {
        'author': 'Asura Samsara',
        'title': 'Blog post 1',
        'content': 'Oonga Boonga',
        'date_posted': 'April 25, 2018'  # Fix the key name to 'date_posted'
    },
    {
        'author': 'Rogal Dorne',
        'title': 'Praetorian of Man',
        'content': 'Build walls',
        'date_posted': 'May 20, 2018'  # Fix the key name to 'date_posted'
    }
]

@app.route("/")
@app.route("/home")
def home():
    return render_template('home.html', posts=posts)

@app.route("/about")
def about():
    return render_template('about.html', title='About')

@app.route("/register", methods=['GET', 'POST'])
def register():
    form = RegistrationForm()
    if form.validate_on_submit():
        flash(f'Account created for {form.username.data}!', 'success')
        return redirect(url_for('home'))

    return render_template('register.html', title='Register', form=form)

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
    app.run(debug=True)
