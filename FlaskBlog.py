


from models import User,Post



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
