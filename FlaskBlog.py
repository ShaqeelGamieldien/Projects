from flask import *
from flask_sqlalchemy import SQLAlchemy
from forms import RegistrationForm, LoginForm  # Make sure the import paths are correct
from datetime import datetime  # Correct the import of datetime
from models import User,Post


app = Flask(__name__)
app.config['SECRET_KEY'] = '40bfaaf7cf15c237128ef5ae12c62e85'
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///site.db'  # Correct the config key name

db = SQLAlchemy(app)
app.app_context().push()




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
