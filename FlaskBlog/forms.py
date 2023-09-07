from flask_wtf import *
from flask_wtf.file import FileField, FileAllowed
from wtforms import *
from wtforms.validators import *
import email_validator
from FlaskBlog.models import *
from flask_login import current_user

class RegistrationForm(FlaskForm):
        username=StringField('Username',
                            validators = [DataRequired(), Length(min =2, max =20 )]

                            )
        email = StringField('Email',     validators = [DataRequired(), Email()])
        password = PasswordField('Confirm Password',    validators = [DataRequired()])
        confirm_password = PasswordField('Confirm Password',    validators = [DataRequired(), EqualTo('password')])


        submit=SubmitField('Sign Up')
        def validate_username(self, username):
            user = User.query.filter_by(username=username.data).first()
            if user:
                raise ValidationError('That username is taken. Please choose a different one.')

        def validate_email(self, email):
            user = User.query.filter_by(email=email.data).first()
            if user:
                raise ValidationError('That email is taken. Please choose a different one.')



class LoginForm(FlaskForm):

        email = StringField('Email',     validators = [DataRequired(), Email()])
        password = PasswordField('Confirm Password',    validators = [DataRequired()])
        remember= BooleanField('Remember Me')
        submit=SubmitField('Sign Up')

class UpdateForm(FlaskForm):
    username = StringField('Username',
                           validators=[DataRequired(), Length(min=2, max=20)])
    email = StringField('Email',
                        validators=[DataRequired(), Email()])
    picture =FileField('Update Profile', validators=[FileAllowed(['jpg','png'])])
    submit = SubmitField('Update')

    def validate_username(self, username):
        if username.data != current_user.username:
            user = User.query.filter_by(username=username.data).first()
            if user:
                raise ValidationError('That username is taken. Please choose a different one.')

    def validate_email(self, email):
        if email.data != current_user.email:
            user = User.query.filter_by(email=email.data).first()
            if user:
                raise ValidationError('That email is taken. Please choose a different one.')
