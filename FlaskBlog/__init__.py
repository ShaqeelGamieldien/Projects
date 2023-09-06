from flask import *
from flask_sqlalchemy import SQLAlchemy
from flask_bcrypt import Bcrypt
from datetime import datetime  # Correct the import of datetime
from  flask_login import LoginManager


app = Flask(__name__)
app.config['SECRET_KEY'] = '40bfaaf7cf15c237128ef5ae12c62e85'
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///site.db'  # Correct the config key name

db = SQLAlchemy(app)
app.app_context().push()
bcrypt = Bcrypt(app)
login_manager= LoginManager(app)
from FlaskBlog import routes
