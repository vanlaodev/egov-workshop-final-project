function promiseDelay(duration) {
    return new Promise(resolve => {
        setTimeout(resolve, duration);
    });
}

export {
    promiseDelay
};