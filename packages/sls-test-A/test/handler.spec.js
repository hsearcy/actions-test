const hello = require("../handler");

describe("handler", () => {
    it("returns message A", async () => {
        const response = await hello.hello();
        expect(JSON.parse(response.statusCode)).toBe(200);
    });
});
