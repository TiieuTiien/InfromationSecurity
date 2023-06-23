document.addEventListener("DOMContentLoaded", () => {
    var fileInput = document.getElementById("fileInput");
    const fileContent = document.getElementById("plaintextArea");
    const etxtButton = document.getElementById("etxtButton");
    const exmlButton = document.getElementById("exmlButton");
    const edocxButton = document.getElementById("edocxButton");

    const cipherContent = document.getElementById("ciphertextArea");
    const txtButton = document.getElementById("txtButton");
    const xmlButton = document.getElementById("xmlButton");
    const docxButton = document.getElementById("docxButton");

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

    etxtButton.addEventListener("click", () => {
        downloadFile("file.txt", fileContent.value, "text/plain");
    });

    exmlButton.addEventListener("click", () => {
        const xmlContent = fileContent.value;
        if (isXmlFormat(xmlContent)) {
            downloadFile("file.xml", xmlContent, "text/xml");
        } else {
            alert("Invalid XML format. Proceeding with download.");
            downloadFile("file.xml", xmlContent, "text/xml");
        }
    });

    edocxButton.addEventListener("click", () => {
        const xmlContent = fileContent.value;
        if (isXmlFormat(xmlContent)) {
            convertXmlToDocx(xmlContent);
        } else {
            alert("Converting to DOCX format.");
            downloadFile("file.docx", xmlContent, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        }
    });

    txtButton.addEventListener("click", () => {
        downloadFile("file.txt", cipherContent.value, "text/plain");
    });

    xmlButton.addEventListener("click", () => {
        const xmlContent = cipherContent.value;
        if (isXmlFormat(xmlContent)) {
            downloadFile("file.xml", xmlContent, "text/xml");
        } else {
            alert("Invalid XML format. Proceeding with download.");
            downloadFile("file.xml", xmlContent, "text/xml");
        }
    });

    docxButton.addEventListener("click", () => {
        const xmlContent = cipherContent.value;
        if (isXmlFormat(xmlContent)) {
            convertXmlToDocx(xmlContent);
        } else {
            alert("Converting to DOCX format.");
            downloadFile("file.docx", xmlContent, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        }
    });

    function convertXmlToDocx(xmlContent) {
        const zip = new JSZip();
        const root = zip.folder("word");
        const document = root.folder("document.xml");
        document.file("content.xml", xmlContent);

        zip.generateAsync({ type: "blob" })
            .then((blob) => {
                const downloadLink = document.createElement("a");
                downloadLink.href = URL.createObjectURL(blob);
                downloadLink.download = "converted.docx";
                downloadLink.click();
            })
            .catch((error) => {
                console.error("Error converting XML to DOCX:", error);
            });
    }

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

    function downloadFile(fileName, content, mimeType) {
        const blobContent = new Blob([content], { type: mimeType });

        // Create a temporary <a> element to download the file
        const downloadLink = document.createElement("a");
        downloadLink.href = URL.createObjectURL(blobContent);
        downloadLink.download = fileName;

        // Simulate a click on the link to trigger the download
        document.body.appendChild(downloadLink);
        downloadLink.click();

        // Clean up
        document.body.removeChild(downloadLink);
        URL.revokeObjectURL(downloadLink.href);
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
