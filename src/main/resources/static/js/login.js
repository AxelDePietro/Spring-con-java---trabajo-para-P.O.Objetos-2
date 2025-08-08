document.addEventListener("DOMContentLoaded", () => {
    const toggleButtons = document.querySelectorAll(".toggle-password");

    toggleButtons.forEach((toggleButton) => {
        const passwordWrapper = toggleButton.closest(".password-wrapper");
        if (!passwordWrapper) return;

        const passwordInput = passwordWrapper.querySelector("input[type='password'], input[type='text']");
        const toggleIcon = toggleButton.querySelector("img");

        if (!passwordInput || !toggleIcon) return;

        let visible = false;

        toggleButton.addEventListener("click", () => {
            visible = !visible;
            passwordInput.type = visible ? "text" : "password";
            toggleIcon.src = visible ? "css/ojo-abierto.svg" : "css/ojo-cerrado.svg";
            toggleIcon.alt = visible ? "Ocultar contraseña" : "Mostrar contraseña";
        });
    });
});
