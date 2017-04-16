<?php
echo"33";
?>


<div class="container">

  <ul>
    <li>Trykk på cellene for å redigere, enkelte celler vil ikke være mulig å redigere grunnet sikkerhet</li> 
    <li>Click on a cell to edit, some cells may not be changed for security reasons</li>
  </ul>

  <div id="table" class="table-editable">
      
       <div id="myAlert" class="alert success fade" data-alert="alert">This is the alert.</div>
    <table class="table table-hover table-responsive " id ="liste">
        <thead>
      <tr>
        <th>#</th>
        <th>Åpningstider</th>
        <th>Dag</th> 
      </tr>
        </thead>
        <tbody id="listeBody">
      
     
        </tbody>
    </table>
  </div>
  

</div>
    

    
<script src="fillHours.JS"></script>
<script src="editableTable.js"></script>

    
    