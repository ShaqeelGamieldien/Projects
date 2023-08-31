from flask import *
from flask_sqlalchemy import SQLAlchemy
from forms import RegistrationForm, LoginForm


app = Flask(__name__)
app.config['SECRET_KEY'] = '40bfaaf7cf15c237128ef5ae12c62e85'
app.config['SQLAlCHEMY_DATABASE_URI'] = 'sqlite:///site.db'
db=SQLAlchemy(app)


class User(db.Model):
    id = db.Column(db.String, primary_key = True)
    username = db.Column(db.String(20), unique = True, nullable=False)
    email = db.Column(db.String(120), unique = True, nullable=False)
    image_file= db.Column(db.String(20),nullable=False, default= 'default.jpg')




posts = [
 {
  'author' : 'Asura Samsara',
  'title' : 'Blog post 1',
  'content': 'Oonga Boonga',
  'date_poste': 'April 25, 2018'
 },
 {
  'author' : 'Rogal Dorne',
  'title' : 'Praetorian of Man',
  'content': 'Build walls',
  'date_poste': 'May 20, 2018'
 }
]

@app.route("/")
@app.route("/home")
def home():
    return render_template('home.html', posts=posts)

@app.route("/about")
def about():
    return render_template('about.html', title= 'About')


@app.route("/register" , methods=['GET','POST'])
def register():
    form= RegistrationForm()
    if form.validate_on_submit():

        flash(f'Account created for {form.username.data}!', 'success')
        return redirect(url_for('home'))

    return render_template('register.html',title='Register', form=form)


@app.route("/login", methods=['GET','POST'])
def Login():
    form= LoginForm()
    if form.validate_on_submit():
        if form.email.data== 'admin@blog.com' and form.password.data == 'password':
            flash('Logged in successfully!', 'success')
            return redirect(url_for('home'))
        else:
                flash('Log in unsuccessful', 'danger')
    return render_template('login.html',title='Login', form=form)


if __name__ == '__main__':
    app.run(debug=True)
