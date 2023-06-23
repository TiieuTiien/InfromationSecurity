document.addEventListener("DOMContentLoaded", () => {
    var fileInput = document.getElementById("fileInput");
    const fileContent = document.getElementById("plaintextArea");
    const etxtButton = document.getElementById("etxtButton");
    const exmlButton = document.getElementById("exmlButton");
    const edocxButton = document.getElementById("edocxButton");
  
    function resetFileInput() {
      fileInput.value = null;
    }
  
    function handleFileInputChange(event, fileContent) {
      const file = event.target.files[0];
      const reader = new FileReader();
  
      reader.onload = () => {
        const content = reader.result;
  
        if (file.type === "text/plain" || file.name.endsWith(".txt") || file.type === "text/xml" || file.name.endsWith(".xml")) {
          readFileContent(content, fileContent);
        } else if (file.type === "application/vnd.openxmlformats-officedocument.wordprocessingml.document" || file.name.endsWith(".docx")) {
          convertDocxToXml(content, fileContent);
        } else {
          alert("Unsupported file type");
        }
  
        // Show the file name
        fileInput.value = file.name;
  
        // Show the download buttons
        etxtButton.style.display = "block";
        exmlButton.style.display = "block";
        edocxButton.style.display = "block";
  
        resetFileInput(); // Reset the file input field
      };
  
      reader.readAsArrayBuffer(file); // Read the file as text
    }
  
    fileInput.addEventListener("change", (event) => handleFileInputChange(event, fileContent));
  
    function readFileContent(content, fileContent) {
      if (typeof content === "string") {
        fileContent.value = content; // Display the content in the text area
      } else if (content instanceof ArrayBuffer) {
        const decoder = new TextDecoder("utf-8");
        const decodedContent = decoder.decode(content);
        fileContent.value = decodedContent;
      }
    }
  
    function convertDocxToXml(content, fileContent) {
      const zip = new JSZip();
      zip.loadAsync(content)
        .then((zip) => {
          const xmlFile = zip.file("word/document.xml");
          if (xmlFile) {
            return xmlFile.async("string");
          } else {
            throw new Error("Invalid file format.");
          }
        })
        .then((xmlContent) => {
          fileContent.value = xmlContent; // Display the XML content
        })
        .catch((error) => {
          console.error(error);
        });
    }
  
    function isXmlFormat(content) {
      try {
        const parser = new DOMParser();
        parser.parseFromString(content, "text/xml");
        return true;
      } catch (error) {
        return false;
      }
    }
  });
  