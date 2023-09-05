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
