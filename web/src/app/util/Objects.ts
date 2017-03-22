export class Objects {

    public static clone(source: any, target: any): void {
        if(source === target) {
            return;
        }
        for (let name in source) {
            if (typeof (source[name]) !== 'object' && typeof (source[name]) !== 'function') {
                target[name] = source[name];
            } else {
                target[name] = {};
                this.clone(source[name], target[name]);
            }
        }
    }

    public static equals(o1: any, o2: any): boolean {
        if (o1 && o2) {
            for (let name in o1) {
                if (!o2[name] || typeof (o1[name]) !== typeof (o2[name])) {
                    return false;
                } else if (typeof (o1[name]) !== 'object' && typeof (o1[name]) !== 'function') {
                    if (o1[name] !== o2[name]) {
                        return false;
                    }
                } else {
                    if (!this.equals(o1[name], o2[name])) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}