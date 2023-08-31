from flask import Flask, render_template


app = Flask(__name__)

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



if __name__ == '__main__':
    app.run(debug=True)
