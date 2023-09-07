from flask_wtf import *
from wtforms import *
from wtforms.validators import *
import email_validator

class RegistrationForm(FlaskForm):
        username=StringField('Username',
                            validators = [DataRequired(), Length(min =2, max =20 )]

                            )
        email = StringField('Email',     validators = [DataRequired(), Email()])
        password = PasswordField('Confirm Password',    validators = [DataRequired()])
        confirm_password = PasswordField('Confirm Password',    validators = [DataRequired(), EqualTo('password')])


        submit=SubmitField('Sign Up')



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
