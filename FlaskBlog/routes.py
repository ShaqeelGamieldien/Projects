from FlaskBlog.models import User,Post
from flask import *
from FlaskBlog.forms import RegistrationForm, LoginForm
from  FlaskBlog import app,db, bcrypt

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

        hashed_pw =  bcrypt.generate_password_hash(form.password.data).decode('utf-8')
        user=User(username=form.username.data, email=form.email.data, password=hashed_pw)
        db.session.add(user)
        db.session.commit()
        flash('Your account has been created! You can login in now!', 'success')

        return redirect(url_for('login'))

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
