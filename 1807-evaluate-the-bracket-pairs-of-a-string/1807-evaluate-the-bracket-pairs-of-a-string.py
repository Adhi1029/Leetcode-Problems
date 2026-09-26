class Solution:
    def evaluate(self, s: str, knowledge: list[list[str]]) -> str:
        d = dict(knowledge)
        return re.sub(r"\((\w+)\)", lambda m: d.get(m[1], "?"), s)