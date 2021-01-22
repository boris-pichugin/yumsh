#!/usr/bin/python3

# https://github.com/gnab/remark/wiki

with open("lessons.html", "w") as f:
    f.write(\
"""
<!DOCTYPE html>
<html>
  <head>
    <title>Занятия кружка по программированию в 6мл классе ЮМШ. 2020-2021</title>
    <meta charset="utf-8">
    <style>
      @import url(https://fonts.googleapis.com/css?family=Roboto:300,600,300italic);
      @import url(https://fonts.googleapis.com/css?family=Ubuntu+Mono:400,700,400italic);

      body {
        font-family: 'Roboto';
        font-weight: 300
      }
      h1, h2, h3 {
        font-family: 'Roboto';
        font-weight: 600;
      }
      .remark-code, .remark-inline-code { 
        font-family: 'Ubuntu Mono';
      }
    </style>
  </head>
  <body>
    <textarea id="source">
""")
    with open("README.md", "r", encoding="utf-8-sig") as g:
        f.write(g.read())

    f.write(\
"""
    </textarea>
    <script src="remark-latest.min.js">
    </script>
    <script>
      var slideshow = remark.create({
        ratio: '3:2'
      });
    </script>
  </body>
</html>
""")