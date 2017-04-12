

<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="style.css">

    
</head>
    
<body>

<div class="container">
 
  
  <ul>
    <li>An editable table that exports a hash array. Dynamically compiles rows from headers</li> 
    <li>Simple / powerful features such as add row, remove row, move row up/down.</li>
  </ul>
  
    
    
    
    

    
    
    
  <div id="table" class="table-editable">
    <span class="table-add glyphicon glyphicon-plus"></span>
    <table class="table table-striped table-hover table-responsive">
        <thead>
      <tr>
        <th>idMenu</th>
        <th>kategori</th>
        <th>merke</th>
          <th>type</th>
          <th>studentPris</th>
          <th>ansattPris</th>
        <th></th>
        <th></th>
      </tr>
        </thead>
        <tbody id="liste">
      <tr>
        <td contenteditable="true">Stir Fry</td>
        <td contenteditable="true">stir-fry</td>
          <td contenteditable="true">Stir Fry</td>
        <td contenteditable="true">stir-fry</td>
          <td contenteditable="true">Stir Fry</td>
        <td contenteditable="true">stir-fry</td>
          
          
        <td><span class="table-remove glyphicon glyphicon-remove"></span></td>
        <td> <span class="table-up glyphicon glyphicon-arrow-up"></span>
          <span class="table-down glyphicon glyphicon-arrow-down"></span> </td>
      </tr>
      <!-- This is our clonable table line -->
      <tr class="hide">
        <td contenteditable="true">idMenu</td>
        <td contenteditable="true">kategori</td>
          <td contenteditable="true">merke</td>
        <td contenteditable="true">type</td>
          <td contenteditable="true">type</td>
        <td contenteditable="true">ansattPris</td>
        <td>
          <span class="table-remove glyphicon glyphicon-remove"></span>
        </td>
      </tr>
        </tbody>
    </table>
  </div>
  
  <button id="export-btn" class="btn btn-primary">Export Data</button>
  <p id="export"></p>
</div>
    

    
    
    
    
    
    
    
    
    
    
    

    
    
    
</body>
    
    
<script type="text/javascript">
var $TABLE = $('#table');
var $BTN = $('#export-btn');
var $EXPORT = $('#export');

$('.table-add').click(function () {
  var $clone = $TABLE.find('tr.hide').clone(true).removeClass('hide table-line');
  $TABLE.find('table').append($clone);
});

$('.table-remove').click(function () {
  $(this).parents('tr').detach();
});

$('.table-up').click(function () {
  var $row = $(this).parents('tr');
  if ($row.index() === 1) return; // Don't go above the header
  $row.prev().before($row.get(0));
});

$('.table-down').click(function () {
  var $row = $(this).parents('tr');
  $row.next().after($row.get(0));
});

// A few jQuery helpers for exporting only
jQuery.fn.pop = [].pop;
jQuery.fn.shift = [].shift;
 
    
$BTN.click(function () {
  var $rows = $TABLE.find('tr:not(:hidden)');
  var headers = [];
  var data = [];
  
  // Get the headers (add special header logic here)
  $($rows.shift()).find('th:not(:empty)').each(function () {
    headers.push($(this).text().toLowerCase());
  });
  
  // Turn all existing rows into a loopable array
  $rows.each(function () {
    var $td = $(this).find('td');
    var h = {};
    
    // Use the headers from earlier to name our hash keys
    headers.forEach(function (header, i) {
      h[header] = $td.eq(i).text();   
    });
    
    data.push(h);
  });
  
  // Output the result
  $EXPORT.text(JSON.stringify(data));
});    
    
</script>    
     <script src="fillSpecial.JS"></script>
    
    
</html>