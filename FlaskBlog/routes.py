# Import necessary modules and packages
import os
import secrets
from PIL import Image
from flask import render_template, url_for, flash, redirect, request, abort
from FlaskBlog import app, db, bcrypt
from FlaskBlog.forms import RegistrationForm, LoginForm, UpdateForm, PostForm
from FlaskBlog.models import User, Post
from flask_login import login_user, current_user, logout_user, login_required

# Define a route for the home page
@app.route("/")
@app.route("/home")
def home():
    # Retrieve all posts from the database
    posts = Post.query.all()
    return render_template('home.html', posts=posts)

# Define a route for the about page
@app.route("/about")
def about():
    return render_template('about.html', title='About')

# Define a route for user registration
@app.route("/register", methods=['GET', 'POST'])
def register():
    if current_user.is_authenticated:
        return redirect(url_for('home'))  # Redirect already logged in users to the home page
    form = RegistrationForm()  # Create a registration form instance
    if form.validate_on_submit():  # Check if the form was submitted and is valid
        hashed_password = bcrypt.generate_password_hash(form.password.data).decode('utf-8')
        # Create a new user with the provided data
        user = User(username=form.username.data, email=form.email.data, password=hashed_password)
        db.session.add(user)
        db.session.commit()
        flash('Your account has been created! You are now able to log in', 'success')  # Display a success message
        return redirect(url_for('login'))  # Redirect to the login page after successful registration
    return render_template('register.html', title='Register', form=form)

# Define a route for user login
@app.route("/login", methods=['GET', 'POST'])
def login():
    if current_user.is_authenticated:
        return redirect(url_for('home'))  # Redirect already logged in users to the home page
    form = LoginForm()  # Create a login form instance
    if form.validate_on_submit():  # Check if the form was submitted and is valid
        user = User.query.filter_by(email=form.email.data).first()
        # Check if the user exists and the provided password is correct
        if user and bcrypt.check_password_hash(user.password, form.password.data):
            login_user(user, remember=form.remember.data)  # Log in the user
            next_page = request.args.get('next')  # Redirect to the next page if specified
            return redirect(next_page) if next_page else redirect(url_for('home'))
        else:
            flash('Login Unsuccessful. Please check email and password', 'danger')  # Display a danger message
    return render_template('login.html', title='Login', form=form)

# Define a route for user logout
@app.route("/logout")
def logout():
    logout_user()  # Log out the current user
    return redirect(url_for('home'))  # Redirect to the home page

# Define a function to save a user's profile picture
def save_picture(form_picture):
    random_hex = secrets.token_hex(8)
    _, f_ext = os.path.splitext(form_picture.filename)
    picture_fn = random_hex + f_ext
    picture_path = os.path.join(app.root_path, 'static/profile_pics', picture_fn)

    output_size = (125, 125)
    i = Image.open(form_picture)
    i.thumbnail(output_size)
    i.save(picture_path)

    return picture_fn

# Define a route for the user account page
@app.route("/account", methods=['GET', 'POST'])
@login_required  # Require the user to be logged in to access this route
def account():
    form = UpdateForm()  # Create an update form instance
    if form.validate_on_submit():  # Check if the form was submitted and is valid
        if form.picture.data:  # Check if a new profile picture is uploaded
            picture_file = save_picture(form.picture.data)  # Save the new picture
            current_user.image_file = picture_file
        current_user.username = form.username.data
        current_user.email = form.email.data
        db.session.commit()
        flash('Your account has been updated!', 'success')  # Display a success message
        return redirect(url_for('account'))
    elif request.method == 'GET':
        form.username.data = current_user.username
        form.email.data = current_user.email
    image_file = url_for('static', filename='profile_pics/' + current_user.image_file)
    return render_template('account.html', title='Account',
                           image_file=image_file, form=form)

# Define a route for creating a new post
@app.route("/post/new", methods=['GET', 'POST'])
@login_required  # Require the user to be logged in to access this route
def new_post():
    form = PostForm()  # Create a post form instance
    if form.validate_on_submit():  # Check if the form was submitted and is valid
        post = Post(title=form.title.data, content=form.content.data, author=current_user)
        db.session.add(post)
        db.session.commit()
        flash('Your post has been created!', 'success')  # Display a success message
        return redirect(url_for('home'))
    return render_template('create_post.html', title='New Post',
                           form=form, legend='New Post')

# Define a route for viewing a specific post
@app.route("/post/<int:post_id>")
def post(post_id):
    post = Post.query.get_or_404(post_id)
    return render_template('post.html', title=post.title, post=post)

# Define a route for updating a specific post
@app.route("/post/<int:post_id>/update", methods=['GET', 'POST'])
@login_required  # Require the user to be logged in to access this route
def update_post(post_id):
    post = Post.query.get_or_404(post_id)
    if post.author != current_user:
        abort(403)  # Return a forbidden error if the user is not the author of the post
    form = PostForm()  # Create a post form instance
    if form.validate_on_submit():  # Check if the form was submitted and is valid
        post.title = form.title.data
        post.content = form.content.data
        db.session.commit()
        flash('Your post has been updated!', 'success')  # Display a success message
        return redirect(url_for('post', post_id=post.id))
    elif request.method == 'GET':
        form.title.data = post.title
        form.content.data = post.content
    return render_template('create_post.html', title='Update Post',
                           form=form, legend='Update Post')

# Define a route for deleting a specific post
@app.route("/post/<int:post_id>/delete", methods=['POST'])
@login_required  # Require the user to be logged in to access this route
def delete_post(post_id):
    post = Post.query.get_or_404(post_id)
    if post.author != current_user:
        abort(403)  # Return a forbidden error if the user is not the author of the post
    db.session.delete(post)
    db.session.commit()
    flash('Your post has been deleted!', 'success')  # Display a success
