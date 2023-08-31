from flask_wtf import FlaskForm
from wtfoms import StringField, PasswordField, SubmitField, BooleanField
from wtfoms.validators import DataRequired, Length, Email, EqualTo

class RegistrationForm(FlaskForm):
        username=StringField('Username',
                            validators = [DataRequired(), Length(min =2, max =20 )]

                            )
        email = StringField('Email',     validators = [DataRequired(), Email()])
        password = PasswordField('Confirm Password',    validators = [DataRequired())
        confirm_password = PasswordField('Confirm Password',    validators = [DataRequired(), EqualTo('password')]


        submit=SubmitField('Sign Up')



class LoginForm(FlaskForm):

        email = StringField('Email',     validators = [DataRequired(), Email()])
        password = PasswordField('Confirm Password',    validators = [DataRequired())
        remember= BooleanField('Remember Me')
        submit=SubmitField('Sign Up')
