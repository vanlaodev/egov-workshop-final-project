import i18n from "../i18n";

function promiseDelay(duration) {
  return new Promise((resolve) => {
    setTimeout(resolve, duration);
  });
}

function showErrorAlert(err) {
  if (err) {
    const msg = i18n.t("fmt_msg_error", { msg: err });
    alert(msg);
  }
}

export { promiseDelay, showErrorAlert };
