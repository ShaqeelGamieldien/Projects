from flask import *
from forms import RegistrationForm, LoginForm


app = Flask(__name__)
app.config['SECRET_KEY'] = '40bfaaf7cf15c237128ef5ae12c62e85'
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


@app.route("/login")
def Login():
    form= RegistrationForm()
    return render_template('login.html',title='Login', form=form)


if __name__ == '__main__':
    app.run(debug=True)
