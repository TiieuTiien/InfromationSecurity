document.addEventListener("DOMContentLoaded", () => {

    const encryptedKeyArea = document.getElementById("encryptedKeyArea");
    const privateKeyArea = document.getElementById("privateKeyArea");
    const publicKeyArea = document.getElementById("publicKeyArea");

    const fileContent = document.getElementById("plaintextArea");
    const etxtButton = document.getElementById("etxtButton");
    const exmlButton = document.getElementById("exmlButton");
    const edocxButton = document.getElementById("edocxButton");

    const cipherContent = document.getElementById("ciphertextArea");
    const txtButton = document.getElementById("txtButton");
    const xmlButton = document.getElementById("xmlButton");
    const docxButton = document.getElementById("docxButton");

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
        const content = `Encrypted Key:\n\n${encryptedKeyArea.value}\n\nPubulic Key:\n\n${publicKeyArea.value}\n\nPrivate Key:\n\n${privateKeyArea.value}\n\nCiphertext:\n\n${cipherContent.value}`;
        downloadFile("decrypted.txt", content, "text/plain");
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

    function downloadFile(fileName, content, mimeType) {
        let processedContent = content;

        // Adjust line breaks based on the file format
        if (mimeType === "text/plain") {
            processedContent = content.replace(/\n/g, "\r\n"); // Replace \n with \r\n for plain text files
        }

        const blobContent = new Blob([processedContent], { type: mimeType });

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
