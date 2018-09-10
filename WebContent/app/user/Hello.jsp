<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><!DOCTYPE html>
<html>
  <head>
    <meta charset='utf-8'>
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href='https://fonts.googleapis.com/css?family=Architects+Daughter' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="stylesheets/stylesheet.css" media="screen">

    <link rel="stylesheet" type="text/css" href="stylesheets/print.css" media="print">
    <link rel="stylesheet" type="text/css" href="stylesheets/simple-calendar.css">

    <title>Simple-calendar by Lixucheng</title>
    <style>
      #calendar{
        width: 100%
      }
      .clearfix{
        margin-bottom: 50px !important;
      }
    </style>
  </head>

  <body>


    <div id="content-wrapper">
  <div class="inner clearfix">
      <div id = 'calendar'>

      </div>
    </div>

   
    <script type="text/javascript" src="javascripts/simple-calendar.js"></script>
    <script>
     var myCalendar = new SimpleCalendar('#calendar');
    </script>


  </body>
</html>
